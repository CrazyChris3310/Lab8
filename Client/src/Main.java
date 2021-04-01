import com.opencsv.exceptions.CsvValidationException;
import input.ConsoleInput;
import utilities.DragonCollection;
import utilities.Process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

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


//        Path path = Paths.get("Files\\bank.csv");


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
        Process proc = new Process(dragons, new ConsoleInput());

        proc.defineCommand();
    }
}
