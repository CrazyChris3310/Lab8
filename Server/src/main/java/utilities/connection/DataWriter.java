package utilities.connection;

import org.apache.logging.log4j.Logger;
import utilities.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;

public class DataWriter implements Runnable {

    Logger logger;
    Response response;

    public DataWriter(Logger logger, Response response) {
        this.logger = logger;
        this.response = response;
    }

    @Override
    public void run() {

        SocketChannel channel = response.getDestination();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(response);

            ByteBuffer buf = ByteBuffer.wrap(baos.toByteArray());
            do {
                channel.write(buf);
            } while (buf.hasRemaining());

            oos.close();
            baos.close();
        } catch (IOException e) {
            logger.error("IOException happened while sending file. " + e.getMessage());
        }

    }
}
