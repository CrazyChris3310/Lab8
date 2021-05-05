package utilities;

import utilities.commands.Command;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;

public class ConnectionManager {

    private  final SocketAddress adr;

    private Socket socket;

    ByteBuffer buf;

    public ConnectionManager(String ip, int port) throws UnknownHostException, IOException {
        buf = ByteBuffer.allocate(10000);
        adr = new InetSocketAddress(ip, port); // address of server
        socket = new Socket();
    }

    public void connect() throws IOException{
        socket.connect(adr);
    }

    public void send(Command command) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(command);
        baos.writeTo(socket.getOutputStream());
    }


    public Response receive() throws ClassNotFoundException, IOException{
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return (Response) ois.readObject();
    }

}