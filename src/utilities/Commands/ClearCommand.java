package utilities.Commands;

import Input.Input;
import utilities.DragonCollection;

/**
 * Command "clear". Clears the collection.
 */
public class ClearCommand extends Command{


    public ClearCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    /**
     * Method removes everything from collection.
     */
    @Override
    public void execute() {
        drg.clear();
    }
}
