package utilities;

import utilities.commands.Command;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class ConnectionManager {

    private ByteBuffer buf;

    private SocketAddress adr;

    private ServerSocketChannel socketChannel;
    private SocketChannel channel;

    private SocketAddress remoteAdr;

    public ConnectionManager(int port) throws AlreadyBoundException, IOException {
        buf = ByteBuffer.allocate(10000);
        adr = new InetSocketAddress(port);
        socketChannel = ServerSocketChannel.open();
        socketChannel.bind(adr);
        socketChannel.configureBlocking(false);
    }

    public void connect() {
        try {
            channel = null;
            do {
                channel = socketChannel.accept();
            } while (channel == null);
            channel.configureBlocking(false);
            remoteAdr = channel.getRemoteAddress();
        } catch (IOException e) {
            System.out.println("IOException happened");
        }
    }

    public SocketAddress getLocalAddress() {
        return adr;
    }

    public SocketAddress getRemoteAddress() {
        return remoteAdr;
    }

    public Command receiveCommand() throws IOException, ClassNotFoundException{

        buf.clear();
        int bytesRead;
        do {
            bytesRead = channel.read(buf);
        } while (bytesRead == 0);

        buf.flip();

        Command command;

        try (ByteArrayInputStream bais = new ByteArrayInputStream(buf.array());
             ObjectInputStream ois = new ObjectInputStream(bais) ) {
            command = (Command) ois.readObject();
        }
        return command;
    }

    public void send(Response response) throws IOException{

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(response);
            channel.write(ByteBuffer.wrap(baos.toByteArray()));
        }

    }


}
