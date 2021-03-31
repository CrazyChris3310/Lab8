package utilities.commands;

import input.Input;
import utilities.DragonCollection;

/**
 * Command "clear". Clears the collection.
 */
public class ClearCommand extends Command{


    public ClearCommand(DragonCollection collection, Input input) {
        super(collection, input);
        description = "clear - remove everything from collection";
    }

    /**
     * Method removes everything from collection.
     */
    @Override
    public void execute() {
        if (isInputStreamNotEmpty())
            return;

        drg.clear();
    }
}
