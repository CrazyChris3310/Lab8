package utilities.Commands;

import Input.Input;
import utilities.DragonCollection;

/**
 * Command "remove_first".
 */
public class RemoveFirstCommand extends Command{


    public RemoveFirstCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    /**
     * Method removes first element from collection.
     */
    @Override
    public void execute() {
        drg.removeFirst();
    }
}
