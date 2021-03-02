package utilities.Commands;

import Exceptions.WrongInputFormatException;
import Input.FileInput;
import Input.Input;
import utilities.DragonCollection;
import utilities.Process;

import java.io.FileNotFoundException;

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
        String file;
        try {
            file = input.inputFilePath();
        } catch (WrongInputFormatException e) {
            System.out.println("Wrong script path format");
            return;
        }

        Input inp;
        try {
            inp = new FileInput(file);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
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
