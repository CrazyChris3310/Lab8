package utilities.commands;

import input.Input;

/**
 * Command "clear". Clears the collection.
 */
public class ClearCommand extends Command{

    public ClearCommand(Input input) {
        super(input);
        description = "clear - remove everything from collection";
    }

    /**
     * Method removes everything from collection.
     */
    @Override
    public void execute() {
    }
}
