package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.connection.*;
import utilities.dataBase.DataBaseConnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.AlreadyBoundException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Process {

    private SocketAddress adr;
    private DragonCollection collection;
    private ConnectionAccepter accepter;

    private ArrayList<SocketChannel> clients;
    private ArrayList<Future<Request>> receivedData;
    private ArrayList<Future<Response>> executedData;

    private ExecutorService executor;
    private ReentrantLock locker;

    private static Logger logger = LogManager.getLogger();
    private DataBaseConnection dbc;

    public Process(DragonCollection drg, DataBaseConnection dbc, int port) throws AlreadyBoundException, IOException {
        adr = new InetSocketAddress(port);
        collection = drg;

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(adr);
        serverSocketChannel.configureBlocking(false);
        accepter = new ConnectionAccepter(serverSocketChannel, logger);

        clients = new ArrayList<>();
        receivedData = new ArrayList<>();
        executedData = new ArrayList<>();

        executor = Executors.newCachedThreadPool();
        locker = new ReentrantLock();
        this.dbc = dbc;

        logger.info("Server has started at " + serverSocketChannel.getLocalAddress());
    }

    public void run() {
        while (true) {

            try {
                SocketChannel channel = accepter.accept();
                if (channel != null) {
                    clients.add(channel);
                }
            } catch (SocketException e) {
                logger.info("Client has disconnected");
            } catch (IOException e) {
                logger.warn("IOException happened while trying to accept client", e);
            }

            for (Iterator<SocketChannel> iterator = clients.iterator(); iterator.hasNext(); ) {
                SocketChannel channel = iterator.next();
                FutureTask<Request> result = new FutureTask<>(new DataReceiver(logger, channel));
                receivedData.add(result);
                new Thread(result).start();
                iterator.remove();
            }

            for (Iterator<Future<Request>> iterator = receivedData.iterator(); iterator.hasNext(); ) {
                Future<Request> requestFuture = iterator.next();
                if (requestFuture.isCancelled()) {
                    logger.warn("Receiving task was cancelled");
                    iterator.remove();
                } else if (requestFuture.isDone()) {
                    try {
                        Request request = requestFuture.get();
                        FutureTask<Response> futureResponse = new FutureTask<>(
                                new CommandExecutor(request, collection, logger, locker, dbc));
                        executedData.add(futureResponse);
                        executor.submit(futureResponse);
                    } catch (ExecutionException e) {
                        logger.warn("Computation of receiving thread wasn't successful. " + e.getMessage());
                    } catch (InterruptedException e) {
                        logger.warn("Thread interrupted. " + e.getMessage());
                    }
                    iterator.remove();
                }
            }

            for (Iterator<Future<Response>> iterator = executedData.iterator(); iterator.hasNext(); ) {
                Future<Response> responseFuture = iterator.next();
                if (responseFuture.isCancelled()) {
                    logger.warn("Sending was unsuccessful");
                    iterator.remove();
                } else if (responseFuture.isDone()) {
                    try {
                        Response response = responseFuture.get();
                        new Thread(new DataWriter(logger, response)).start();
                    } catch (ExecutionException e) {
                        logger.warn("Computation of receiving thread wasn't successful", e);
                    } catch (InterruptedException e) {
                        logger.warn("Thread interrupted", e);
                    }
                    iterator.remove();
                }
            }
        }
    }


}
