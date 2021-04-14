package utilities;

import dragon.Coordinates;
import dragon.Dragon;
import dragon.DragonType;
import utilities.commands.AddCommand;
import utilities.commands.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.ByteBuffer;

public class ConnectionManager {

    private DragonCollection dragons;
    private final int port;
    ByteBuffer buf;


    SocketAddress adr;
    DatagramSocket socket;


    // TODO: Socket exception should be processed
    public ConnectionManager(int port) throws SocketException {
        this.port = port;
        buf = ByteBuffer.allocate(1000);
        adr = new InetSocketAddress(port);
        socket = new DatagramSocket(port);
    }

    public void run() {
        buf.clear();

        AddCommand add;

        while (true) {
            DatagramPacket receivingPacket = new DatagramPacket(buf.array(), buf.limit());
            try {
                socket.receive(receivingPacket);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            buf.flip();
            ByteArrayInputStream bais = new ByteArrayInputStream(buf.array());

            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                add = (AddCommand) ois.readObject();
                System.out.println(add.getDragon());
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
