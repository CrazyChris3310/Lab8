package utilities;

import utilities.commands.Command;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class ConnectionManager {

    private final int port;
    ByteBuffer buf;

    SocketAddress adr;
    DatagramSocket socket;

    SocketAddress clientAdr;

    public ConnectionManager(int port) throws SocketException {
        this.port = port;
        buf = ByteBuffer.allocate(10000);
        adr = new InetSocketAddress(port);
        socket = new DatagramSocket(port);
        System.out.println("The server is started on " + adr);
    }

    public Command receiveCommand() {
        buf.clear();
        DatagramPacket toReceive = new DatagramPacket(buf.array(), buf.limit());
        try {
            socket.receive(toReceive);
        } catch (IOException e) {
            System.out.println("Error while reading");
            e.printStackTrace();
        }
        clientAdr = new InetSocketAddress(toReceive.getAddress(), toReceive.getPort());
        buf.flip();

        try (ByteArrayInputStream bais = new ByteArrayInputStream(buf.array());
             ObjectInputStream ois = new ObjectInputStream(bais)) {

            return (Command) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("SomeError");
            return null;
        }
    }

//    public void sendCommandResult(ArrayList<String> list) {
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
//             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
//
//            oos.writeObject(list);
//            DatagramPacket toSend = new DatagramPacket(baos.toByteArray(), baos.size(), clientAdr);
//            socket.send(toSend);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sendErrorMessage(String message) {
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
//             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
//
//            oos.writeObject(message);
//            DatagramPacket toSend = new DatagramPacket(baos.toByteArray(), baos.size(), clientAdr);
//            socket.send(toSend);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public <T> void send(T obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            oos.writeObject(obj);
            DatagramPacket toSend = new DatagramPacket(baos.toByteArray(), baos.size(), clientAdr);
            socket.send(toSend);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
