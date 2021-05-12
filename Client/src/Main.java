import input.ConsoleInput;
import utilities.ConnectionManager;
import utilities.Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int port;
        String ip;

        ConnectionManager cm;

        System.out.print("Enter the ip address of server: ");
        ip = sc.nextLine().trim();

        while (true) {
            try {
                System.out.print("Enter the port of server: ");
                port = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong port format");
            }
        }
        cm = new ConnectionManager(ip, port);

        Process process = new Process(new ConsoleInput(), cm);
        process.defineCommand();

    }
}
