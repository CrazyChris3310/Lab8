import input.ConsoleInput;
import utilities.ConnectionManager;
import utilities.Process;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int port;
        String ip;

        ConnectionManager cm;

        System.out.print("Enter the ip address of server: ");
        ip = sc.nextLine().trim();

        String str;

        while (true) {
            System.out.print("Enter the port of server: ");
            str = sc.nextLine().trim();
            if (str.matches("^\\d{1,5}$")) {
                port = Integer.parseInt(str);
                break;
            }
            System.out.println("Wrong port format");
        }

        port = Integer.parseInt(str);
        cm = new ConnectionManager(ip, port);

        Process process = new Process(new ConsoleInput(), cm);
        process.defineCommand();
    }
}
