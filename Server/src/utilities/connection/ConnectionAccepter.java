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

//    public static void accept(SelectionKey key) {
//        try {
//            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
//            SocketChannel channel = ssc.accept();
//            channel.configureBlocking(false);
//            Selector selector = key.selector();
//            channel.register(selector, SelectionKey.OP_READ);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public SocketChannel accept() throws IOException {
        SocketChannel channel;
        do {
            channel = serverSocketChannel.accept();
        } while (channel == null);
        logger.info("Client at " + channel.getRemoteAddress() + " has connected");
        channel.configureBlocking(false);

        return channel;
    }

}
