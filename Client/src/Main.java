import com.opencsv.exceptions.CsvValidationException;
import input.ConsoleInput;
import utilities.ConnectionManager;
import utilities.DragonCollection;
import utilities.Process;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int port;
        String ip;

        System.out.println("Enter the ip address of server: ");
        ip = sc.nextLine().trim();
        System.out.println("Enter the port of server: ");
        port = Integer.parseInt(sc.nextLine());

        ConnectionManager cm;
        while (true) {
            try {
                cm = new ConnectionManager(ip, port);
                cm.run();
                break;
            } catch (SocketException e) {
                System.out.println("Port is out of range");
            } catch (IOException e) {
                System.out.println("IOException occurred");
            }
        }

        Process process = new Process(new ConsoleInput(), cm);
        process.defineCommand();

    }
}
