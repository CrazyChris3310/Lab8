package utilities.commands;

import input.ConsoleInput;
import input.Input;

/**
 * Command "info".
 */
public class InfoCommand extends Command{

    private static final long serialVersionUID = 108L;

    public InfoCommand(Input input) {
        super(input);
        name = "info";
        description = "show information about collection";
    }

    public InfoCommand() {
        this(new ConsoleInput());
    }

    /**
     * Method shows information about collection.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();

    }
}
