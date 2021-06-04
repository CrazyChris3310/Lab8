package utilities.commands;

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

    /**
     * Method removes everything from collection.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();
    }
}
