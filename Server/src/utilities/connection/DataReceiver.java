package utilities.connection;

import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DataReceiver {

    Logger logger;

    public DataReceiver(Logger logger) {
        this.logger = logger;
    }

    public byte[] receiveCommand(SocketChannel channel) throws IOException {

        ByteBuffer buf = ByteBuffer.allocate(4);
        do {
            channel.read(buf);
        } while (buf.hasRemaining());

        buf.flip();
        int arraySize = buf.getInt();

        buf = ByteBuffer.allocate(arraySize);

        while (buf.hasRemaining()) {
            channel.read(buf);
        }

        logger.info("Data received");

        return buf.array();
    }

}
