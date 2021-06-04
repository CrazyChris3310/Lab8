package utilities;

import exceptions.ServerUnavailableException;
import utilities.commands.Command;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.HashSet;

public class ConnectionManager {

    private final SocketAddress adr;
    private Socket socket;

    private HashSet<Path> paths = new HashSet<>();

    public ConnectionManager(String ip, int port) {
        adr = new InetSocketAddress(ip, port); // address of server
    }

    public HashSet<Path> getPaths() {
        return paths;
    }

    public void addToPaths(Path p) {
        paths.add(p);
    }

    public void connect() throws ServerUnavailableException {
        long start = System.currentTimeMillis();
        while (true) {
            try {
                socket = new Socket();
                socket.connect(adr);
                break;
            } catch (IOException ignored) {
                long end = System.currentTimeMillis();
                if (end - start > 5000) {
                    throw new ServerUnavailableException();
                }
            }
        }
    }

    public void send(Request request) throws IOException {
        OutputStream os = socket.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(request);
        os.write(ByteBuffer.allocate(4).putInt(baos.size()).array());
        baos.writeTo(os);
    }


    public Response receive() throws ClassNotFoundException, IOException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return (Response) ois.readObject();
    }

    public Socket getSocket() {
        return socket;
    }

}