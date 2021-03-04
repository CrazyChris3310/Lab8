package utilities.commands;

import exceptions.WrongInputFormatException;
import exceptions.WrongPathRightsException;
import input.FileInput;
import input.Input;
import utilities.DragonCollection;
import utilities.Process;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * Command "ExecuteCommand". Executes script.
 */
public class ExecuteScriptCommand extends Command{

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

        if (drg.getScripts().contains(path)) {
            System.out.println("To prevent stack overflow error script was stopped");
            return;
        }
        drg.addToPathSet(path);

        Input inp;
        try {
            inp = new FileInput(path);
        } catch (IOException e) {
            System.out.println("Unexpected error happened while reading a file");
            return;
        }

        Process fileReader = new Process(drg,inp);
        fileReader.defineFileCommand();
    }

}
