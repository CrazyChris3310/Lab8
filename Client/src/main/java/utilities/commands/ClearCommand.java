package utilities.commands;

import input.ConsoleInput;
import input.Input;

/**
 * Command "clear". Clears the collection.
 */
public class ClearCommand extends Command{

    private static final long serialVersionUID = 103L;
    public ClearCommand(Input input) {
        super(input);
        name = "clear";
        description = "remove everything from collection";
    }

    public ClearCommand() {
        this(new ConsoleInput());
    }

    /**
     * Method removes everything from collection.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();
    }
}
