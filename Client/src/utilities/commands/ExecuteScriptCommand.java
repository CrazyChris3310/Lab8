package utilities.commands;

import exceptions.WrongInputFormatException;
import exceptions.WrongPathRightsException;
import input.FileInput;
import input.Input;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import utilities.ConnectionManager;
import utilities.Process;

/**
 * Command "ExecuteCommand". Executes script.
 */
public class ExecuteScriptCommand extends Command{

    public ExecuteScriptCommand(Input input) {
        super(input);
        description = "execute_script file_name - read and execute script from given file";
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

//        if (drg.getScripts().contains(path)) {
//            System.out.println("To prevent stack overflow error script was stopped");
//            return;
//        }
//        drg.addToPathSet(path);
//
//        Process fileReader = new Process(inp, new ConnectionManager());
//        fileReader.defineFileCommand();
//
//        drg.getScripts().remove(path);
    }

}
