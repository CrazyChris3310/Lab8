import Input.ConsoleInput;
import utilities.DragonCollection;
import utilities.Process;

import java.io.File;

public class Main {
    public static void main(String[] args) {
       if (args.length != 1) {
           System.out.println("Wrong files");
           return;
       }
       File path = new File(args[0]);
       if (!path.exists()) {
           System.out.println("File not found!");
           return;
       }

        DragonCollection dragons = new DragonCollection(path);
        Process proc = new Process(dragons, new ConsoleInput());
        proc.defineCommand();
    }
}
