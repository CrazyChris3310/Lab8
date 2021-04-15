package utilities.commands;

import exceptions.WrongInputFormatException;
import exceptions.WrongPathRightsException;
import input.FileInput;
import input.Input;
import utilities.ConnectionManager;
import utilities.Process;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;



/**
 * Command "ExecuteCommand". Executes script.
 */
public class ExecuteScriptCommand extends Command{

    ConnectionManager cManager;

    public ExecuteScriptCommand(Input input, ConnectionManager cm) {
        super(input);
        cManager = cm;
        description = "execute_script file_name - read and execute script from given file";
    }

    /**
     * Method inputs file path to script and executes it if it's valid.
     * @return
     */
    @Override
    public boolean execute() {
        Path path;
        try {
            path = input.inputFilePath();
        } catch (WrongInputFormatException e) {
            System.out.println("Wrong script path format");
            return false;
        } catch (WrongPathRightsException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (InvalidPathException e) {
            System.out.println("Invalid path");
            return false;
        }


        Input inp;
        try {
            inp = new FileInput(path);
        } catch (IOException e) {
            System.out.println("Unexpected error happened while reading a file");
            return false;
        }

//        if (drg.getScripts().contains(path)) {
//            System.out.println("To prevent stack overflow error script was stopped");
//            return;
//        }
//        drg.addToPathSet(path);
            Process fileReader = new Process(inp, cManager);
            fileReader.defineFileCommand();
//
//        drg.getScripts().remove(path);
        return true;
    }

}
