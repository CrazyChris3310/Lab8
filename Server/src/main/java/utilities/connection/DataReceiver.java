package utilities.connection;

import org.apache.logging.log4j.Logger;
import utilities.Request;
import utilities.commands.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;

public class DataReceiver implements Callable<Request> {

    Logger logger;
    SocketChannel channel;

    public DataReceiver(Logger logger, SocketChannel channel) {
        this.logger = logger;
        this.channel = channel;
    }

    @Override
    public Request call() {

        Request request = new Request();
        ByteBuffer buf = ByteBuffer.allocate(4);
        try {
            do {
                channel.read(buf);
            } while (buf.hasRemaining());

            ((Buffer)buf).flip();
            int arraySize = buf.getInt();

            buf = ByteBuffer.allocate(arraySize);

            while (buf.hasRemaining()) {
                channel.read(buf);
            }

            ByteArrayInputStream bais = new ByteArrayInputStream(buf.array());
            ObjectInputStream ois = new ObjectInputStream(bais);
            request = (Request) ois.readObject();

            logger.info("Data received");

            ois.close();
            bais.close();

        } catch (IOException e) {
            request.setReceivingError("IOException happened. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            request.setReceivingError("Class was not found. " + e.getMessage());
        }
        request.setSource(channel);

        return request;
    }

}
