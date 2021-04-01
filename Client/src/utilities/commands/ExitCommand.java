package utilities.commands;

import input.Input;
import utilities.DragonCollection;

/**
 * Command "exit".
 */
public class ExitCommand extends Command{


    public ExitCommand(DragonCollection collection, Input input) {
        super(collection, input);
        description = "exit - stop the program without saving";
    }

    @Override
    public void execute() {
        if (isInputStreamNotEmpty())
            return;

        System.exit(0);
    }
}
