package utilities.connection;

import exceptions.NotAllDataReceivedException;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DataReceiver {

    Logger logger;

    public DataReceiver(Logger logger){
        this.logger = logger;
    }

    public byte[] receiveCommand(SocketChannel channel) throws IOException, NotAllDataReceivedException {

        ByteBuffer buf = ByteBuffer.allocate(1000);
        int bytesRead;
        do {
            bytesRead = channel.read(buf);
        } while (bytesRead == 0);

        int ending = buf.getInt(buf.position() - 4);
        if (ending != bytesRead - 4) {
            throw new NotAllDataReceivedException();
        }
        logger.info("Data received");

        byte[] arr = new byte[ending];
        buf.flip();
        buf.get(arr);

        return arr;
    }

}
