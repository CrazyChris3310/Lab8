package utilities.connection;

import exceptions.NotAllDataSendException;
import org.apache.logging.log4j.Logger;
import utilities.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class DataWriter {

    Logger logger;

    public DataWriter(Logger logger) {
        this.logger = logger;
    }

    public void write(SocketChannel channel, Response response) throws IOException, NotAllDataSendException {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(response);
            int bytesWritten = channel.write(ByteBuffer.wrap(baos.toByteArray()));
            if (bytesWritten != baos.size()) {
                throw new NotAllDataSendException();
            }
        }
        logger.info("Response is sent");

    }
}
