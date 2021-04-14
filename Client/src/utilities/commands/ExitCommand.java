package utilities.commands;

import input.Input;
import utilities.ConnectionManager;
import utilities.DragonCollection;

/**
 * Command "exit".
 */
public class ExitCommand extends Command{


    public ExitCommand(Input input, ConnectionManager cm) {
        super(input, cm);
        description = "exit - stop the program without saving";
    }

    @Override
    public void execute() {
        if (isInputStreamNotEmpty())
            return;

        System.exit(0);
    }
}
