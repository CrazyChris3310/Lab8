package utilities;

import input.ConsoleInput;
import input.Input;
import utilities.commands.AddCommand;
import utilities.commands.Command;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ConnectionManager {

    private SocketAddress adr;
    private DatagramChannel channel;

    private int port;
    private String ipAddress;

    ByteBuffer buf;

    public ConnectionManager(String ip, int port) throws SocketException, IOException{
        this.port = port;
        ipAddress = ip;
        buf = ByteBuffer.allocate(10000);
        adr = new InetSocketAddress(ip, port); // address of server
        channel = DatagramChannel.open();
    }


    public void run() {
        Input input = new ConsoleInput();

    }
}
