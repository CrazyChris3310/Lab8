import com.opencsv.exceptions.CsvValidationException;
import utilities.CommandManager;
import utilities.ConnectionManager;
import utilities.DragonCollection;
import utilities.commands.Command;
import utilities.commands.SaveCommand;

import java.io.IOException;
import java.net.SocketException;
import java.nio.channels.AlreadyBoundException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong files");
            return;
        }
        Path path;
        try {
            path = Paths.get(args[0]);
        } catch (InvalidPathException e) {
            System.out.println("Invalid path to file");
            return;
        }

        if (!Files.exists(path)) {
            System.out.println("File does not exist");
            return;
        }
        if (Files.isDirectory(path)) {
            System.out.println("File required, directory found");
            return;
        }
        if (!Files.isReadable(path)) {
            System.out.println("Can not read from this file");
            return;
        }
        if (!Files.isWritable(path)) {
            System.out.println("Can not write to this file");
            return;
        }

        DragonCollection dragons;
        try {
            dragons = new DragonCollection(path.toFile());
        } catch (NumberFormatException | IndexOutOfBoundsException | IOException e) {
            System.out.println("Wrong data in the file");
            return;
        } catch (CsvValidationException e) {
            System.out.println("CSV is wrong");
            return;
        }

        Scanner sc = new Scanner(System.in);

        int port;

        ConnectionManager cm;

        while (true) {
            try {
                System.out.print("Enter the port to bind server to: ");
                port = Integer.parseInt(sc.nextLine());
                cm = new ConnectionManager(port);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong port format");
            } catch (AlreadyBoundException e) {
                System.out.println("This part is already in use. Try another one.");
            } catch (IOException e) {
                System.out.println("IOException happened. Try again.");
            }
        }

        CommandManager commandManager = new CommandManager(cm, dragons);
        try {
            commandManager.startProcess();
        } catch (NoSuchElementException ignored) {
        } finally {
            SaveCommand save = new SaveCommand(dragons);
            save.execute();
        }
        System.out.println("The server has successfully finished");
    }
}
