package utilities.commands;

import input.Input;

import java.time.format.DateTimeFormatter;

/**
 * Command "save".
 */
public class SaveCommand extends Command{


    public SaveCommand(Input input) {
        super(input);
        description = "save - save collection to the file";
    }

    /**
     * Method writes elements of collection into file in csv format.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();

    }
}
