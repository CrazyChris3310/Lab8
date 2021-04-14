package utilities.commands;

import input.Input;
import utilities.ConnectionManager;
import utilities.DragonCollection;

/**
 * Command "clear". Clears the collection.
 */
public class ClearCommand extends Command{

    public ClearCommand(Input input, ConnectionManager cm) {
        super(input, cm);
        description = "clear - remove everything from collection";
    }

    /**
     * Method removes everything from collection.
     */
    @Override
    public void execute() {
    }
}
