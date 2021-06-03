package utilities.connection;

import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ConnectionAccepter {

    ServerSocketChannel serverSocketChannel;
    Logger logger;

    public ConnectionAccepter(ServerSocketChannel ssc, Logger logger) {
        serverSocketChannel = ssc;
        this.logger = logger;
    }


    public SocketChannel accept() throws IOException {
        SocketChannel channel = serverSocketChannel.accept();
        if (channel == null)
            return null;
        logger.info("Client at " + channel.getRemoteAddress() + " has connected");
        channel.configureBlocking(false);

        return channel;
    }

}
