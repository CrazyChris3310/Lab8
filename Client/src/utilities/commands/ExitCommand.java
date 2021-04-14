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
    public void execute() {
        if (isInputStreamNotEmpty())
            return;

        System.exit(0);
    }
}
