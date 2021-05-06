package utilities.commands;

import input.Input;

/**
 * Command "exit".
 */
public class ExitCommand extends Command{

    private static final long serialVersionUID = 105L;

    public ExitCommand(Input input) {
        super(input);
        name = "exit";
        description = "stop client application";
    }

    @Override
    public boolean execute() {
        if (isInputStreamNotEmpty())
            return false;

        return true;
    }
}
