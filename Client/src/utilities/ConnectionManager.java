package utilities;

import utilities.commands.Command;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;

public class ConnectionManager {

    private  final SocketAddress adr;
    private final DatagramChannel channel;

    ByteBuffer buf;

    public ConnectionManager(String ip, int port) throws SocketException, IOException{
        buf = ByteBuffer.allocate(10000);
        adr = new InetSocketAddress(ip, port); // address of server
        channel = DatagramChannel.open();
    }


    public void send(Command command) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            buf.clear();
            oos.writeObject(command);
            buf.put(baos.toByteArray());
            buf.flip();
            channel.send(buf, adr);
        } catch (IOException e) {
            System.out.println("IO error happened");
            e.printStackTrace();
        }
    }

    public ArrayList<String> receiveResult() {
        ArrayList<String> result = new ArrayList<>();
        return receive(result);
//        try (ByteArrayInputStream bais = new ByteArrayInputStream(buf.array());
//             ObjectInputStream ois = new ObjectInputStream(bais)) {
//            buf.clear();
//            channel.receive(buf);
//            buf.flip();
//            result = (ArrayList<String>) ois.readObject();
//        } catch (IOException e) {
//            System.out.println("IO error happened");
//            e.printStackTrace();
//            return null;
//        } catch (ClassNotFoundException e) {
//            System.out.println("Class not found");
//            e.printStackTrace();
//            return null;
//        }
//
//        return result;
    }

    public String receiveErrorMessage() {
        String message = "";
        return receive(message);
    }

    public CommandStatus receiveStatus() {
        CommandStatus status = null;
        return receive(status);
    }

    private <T> T receive(T receiver) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buf.array());
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            buf.clear();
            channel.receive(buf);
            buf.flip();
            receiver = (T) ois.readObject();
        } catch (IOException e) {
            System.out.println("IO error happened");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
            return null;
        }

        return receiver;
    }

}