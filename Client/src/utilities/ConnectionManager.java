package utilities;

import utilities.commands.Command;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class ConnectionManager {

    private  final SocketAddress adr;

    private Socket socket;

    public ConnectionManager(String ip, int port) {
        adr = new InetSocketAddress(ip, port); // address of server
    }

    public void connect() {
        while (true) {
            try {
                socket = new Socket();
                socket.connect(adr);
                break;
            } catch (IOException ignored) {
            }
        }
    }

    public void send(Command command) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(command);
        baos.write(ByteBuffer.allocate(4).putInt(baos.size()).array());
        baos.writeTo(socket.getOutputStream());
    }


    public Response receive() throws ClassNotFoundException, IOException{
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return (Response) ois.readObject();
    }

}