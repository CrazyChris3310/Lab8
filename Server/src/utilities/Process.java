package utilities;

import exceptions.NotAllDataReceivedException;
import exceptions.NotAllDataSendException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.connection.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.AlreadyBoundException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Process {

    private SocketAddress adr;

    private ConnectionAccepter accepter;
    private DataReceiver receiver;
    private CommandExecutor executor;
    private DataWriter writer;

    private static Logger logger = LogManager.getLogger();

    public Process(DragonCollection drg, int port)  throws AlreadyBoundException, IOException {
        adr = new InetSocketAddress(port);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(adr);
        serverSocketChannel.configureBlocking(false);

        accepter = new ConnectionAccepter(serverSocketChannel, logger);
        receiver = new DataReceiver(logger);
        executor = new CommandExecutor(drg, logger);
        writer = new DataWriter(logger);
        logger.info("Server has started at " + serverSocketChannel.getLocalAddress());
    }

    public void run() {
        while (true) {
            byte[] data;
            Response response = new Response();
            SocketChannel channel;

            try {
                channel = accepter.accept();
            } catch (SocketException e) {
              logger.info("Client has disconnected");
              continue;
            } catch (IOException e) {
                logger.warn("IOException happened while trying to accept client", e);
                continue;
            }

            try {
                data = receiver.receiveCommand(channel);
                response = executor.execute(data);
            } catch (SocketException e) {
              logger.info("Client has disconnected");
              continue;
            } catch (IOException e) {
                logger.warn("IOException happened", e);
                response.setMessage("IOException happened");
            } catch (NotAllDataReceivedException e) {
                logger.warn("Not all data from client were received", e);
                response.setMessage("Not all data from client were received");
            } catch (ClassNotFoundException e) {
                logger.warn("Class not found");
                response.setMessage("Class not found");
            }

            try {
                writer.write(channel, response);
            } catch (SocketException e) {
                logger.warn("Client is disconnected");
            } catch (IOException e) {
                logger.warn("IOException has happened", e);
            } catch (NotAllDataSendException e) {
                logger.warn("Not all data was sent to a client");
            }

        }
    }


}
