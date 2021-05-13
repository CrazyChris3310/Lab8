package utilities;

import exceptions.ServerUnavailableException;
import utilities.commands.Command;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class ConnectionManager {

    private final SocketAddress adr;
    private Socket socket;

    public ConnectionManager(String ip, int port) {
        adr = new InetSocketAddress(ip, port); // address of server
    }

    public void connect() throws ServerUnavailableException {
        long start = System.currentTimeMillis();
        while (true) {
            try {
                socket = new Socket();
                socket.connect(adr);
                break;
            } catch (IOException ignored) {
            }
            long end = System.currentTimeMillis();
            if (end - start > 10000) {
                throw new ServerUnavailableException();
            }
        }
    }

    public void send(Command command) throws IOException {
        OutputStream os = socket.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(command);
        os.write(ByteBuffer.allocate(4).putInt(baos.size()).array());
        baos.writeTo(os);
    }


    public Response receive() throws ClassNotFoundException, IOException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return (Response) ois.readObject();
    }

}