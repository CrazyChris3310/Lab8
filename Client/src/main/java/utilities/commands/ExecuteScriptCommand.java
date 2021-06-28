package utilities.commands;

import exceptions.WrongInputFormatException;
import exceptions.WrongPathRightsException;
import input.ConsoleInput;
import input.FileInput;
import input.Input;
import utilities.ConnectionManager;
import utilities.Process;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;


/**
 * Command "ExecuteCommand". Executes script.
 */
public class ExecuteScriptCommand extends Command{

    private static final long serialVersionUID = 104L;
    transient ConnectionManager cManager;
    transient Process proc;
    Path path;

    public ExecuteScriptCommand(Input input, ConnectionManager cm, Process process) {
        super(input);
        cManager = cm;
        proc = process;
        name = "execute_script file_name";
        description = "read and execute script from given file";
        path = null;
    }

    public ExecuteScriptCommand(Process process, File file) {
        this(new ConsoleInput(), process.getConnectionManager(), process);
        path = file.toPath();
    }

    /**
     * Method inputs file path to script and executes it if it's valid.
     * @return
     */
    @Override
    public boolean execute() {
        if (path == null) {
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
        }


        Input inp;
        try {
            inp = new FileInput(path);
        } catch (IOException e) {
            System.out.println("Unexpected error happened while reading a file");
            return false;
        }


        if (cManager.getPaths().contains(path)) {
            System.out.println("To prevent stack overflow error script was stopped");
            return false;
        }
        cManager.addToPaths(path);

        Process fileReader = new Process(inp, cManager, proc.getLogin(), proc.getPassword());
        try {
            fileReader.defineFileCommand();
        } catch (NoSuchElementException e) {
            System.out.println("Wrong data in the file." + e.getMessage());
        }

        cManager.getPaths().remove(path);
        return true;
    }


}
