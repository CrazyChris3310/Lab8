package utilities.connection;

import org.apache.logging.log4j.Logger;
import utilities.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DataWriter {

    Logger logger;

    public DataWriter(Logger logger) {
        this.logger = logger;
    }

    public void write(SocketChannel channel, Response response) throws IOException {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(response);

            ByteBuffer buf = ByteBuffer.wrap(baos.toByteArray());
            do {
                channel.write(buf);
            } while (buf.hasRemaining());

        }

        logger.info("Response is sent");

    }
}
