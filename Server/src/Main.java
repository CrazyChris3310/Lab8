import com.opencsv.exceptions.CsvValidationException;
import utilities.ConnectionManager;
import utilities.DragonCollection;

import java.io.IOException;
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.out.println("Wrong files");
//            return;
//        }
//        Path path;
//        try {
//            path = Paths.get(args[0]);
//        } catch (InvalidPathException e) {
//            System.out.println("Invalid path to file");
//            return;
//        }
//
//        if (!Files.exists(path)) {
//            System.out.println("File does not exist");
//            return;
//        }
//        if (Files.isDirectory(path)) {
//            System.out.println("File required, directory found");
//            return;
//        }
//        if (!Files.isReadable(path)) {
//            System.out.println("Can not read from this file");
//            return;
//        }
//        if (!Files.isWritable(path)) {
//            System.out.println("Can not write to this file");
//            return;
//        }

//
//        Path path = Paths.get("Files\\bank.csv");
//
//
//        DragonCollection dragons;
//        try {
//            dragons = new DragonCollection(path.toFile());
//        } catch (NumberFormatException | IndexOutOfBoundsException | IOException e) {
//            System.out.println("Wrong data in the file");
//            return;
//        } catch (CsvValidationException e) {
//            System.out.println("CSV is wrong");
//            return;
//        }
        Scanner sc = new Scanner(System.in);

        int port;

        while (true) {
            try {
                System.out.print("Enter the port to bind server to: ");
                port = Integer.parseInt(sc.nextLine());
                ConnectionManager cm = new ConnectionManager(port);
                cm.run();
                //TODO: Saving algorithm
                System.out.println("The server has successfully finished");
                return;
            } catch (NumberFormatException e) {
                System.out.println("Wrong port format");
            } catch (SocketException e) {
                System.out.println("This port is not available right know. Try another one");
            }
        }
    }
}