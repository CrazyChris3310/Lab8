package utilities.Commands;

import Exceptions.WrongInputFormatException;
import Exceptions.WrongPathRightsException;
import Input.FileInput;
import Input.Input;
import utilities.DragonCollection;
import utilities.Process;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * Command "ExecuteCommand". Executes script.
 */
public class ExecuteScriptCommand extends Command{

    private static int deepness = 0;

    public ExecuteScriptCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    /**
     * Method inputs file path to script and executes it if it's valid.
     */
    @Override
    public void execute() {
        Path path;
        try {
            path = input.inputFilePath();
        } catch (WrongInputFormatException e) {
            System.out.println("Wrong script path format");
            return;
        } catch (WrongPathRightsException e) {
            System.out.println(e.getMessage());
            return;
        } catch (InvalidPathException e) {
            System.out.println("Invalid path");
            return;
        }


        Input inp;
        try {
            inp = new FileInput(path);
        } catch (IOException e) {
            System.out.println("Unexpected error happened while reading a file");
            return;
        }


        deepness++;
        if (deepness == 100) {
            System.out.println("There is an endless recursion. To prevent stack overflow error the script was stopped");
            deepness = 0;
            return;
        }
        Process fileReader = new Process(drg,inp);
        fileReader.defineFileCommand();
    }

}
