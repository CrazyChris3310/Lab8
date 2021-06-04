import utilities.Process;
import utilities.DragonCollection;

import java.io.IOException;
import java.nio.channels.AlreadyBoundException;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        DragonCollection dragons;
        try {
            dragons = new DragonCollection();
        } catch (NumberFormatException | IndexOutOfBoundsException | IOException e) {
            System.out.println("Wrong data in the file");
            return;
        } catch (SQLException e) {
            System.out.println("Error happened while connecting to database" + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);

        int port;

        Process process;

        while (true) {
            try {
                System.out.print("Enter the port to bind server to: ");
                port = Integer.parseInt(sc.nextLine());
                process = new Process(dragons, port);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong port format");
            } catch (AlreadyBoundException e) {
                System.out.println("This part is already in use. Try another one.");
            } catch (IOException e) {
                System.out.println("IOException happened. " + e.getMessage());
            }
        }

        process.run();
    }
}
