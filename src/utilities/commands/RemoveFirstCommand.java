package utilities.commands;

import input.Input;
import utilities.DragonCollection;

/**
 * Command "remove_first".
 */
public class RemoveFirstCommand extends Command{


    public RemoveFirstCommand(DragonCollection collection, Input input) {
        super(collection, input);
        description = "remove_first - remove the first element from collection";
    }

    /**
     * Method removes first element from collection.
     */
    @Override
    public void execute() {
        input.nextLine();
        drg.removeFirst();
    }
}
