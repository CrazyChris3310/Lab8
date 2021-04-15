package utilities.commands;

import input.Input;

/**
 * Command "exit".
 */
public class ExitCommand extends Command{


    public ExitCommand(Input input) {
        super(input);
        description = "exit - stop the program without saving";
    }

    @Override
    public boolean execute() {
        if (isInputStreamNotEmpty())
            return false;

        System.exit(0);
        return true;
    }
}
