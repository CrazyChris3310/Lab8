package utilities.commands;

import input.Input;
import utilities.DragonCollection;

/**
 * Command "show"
 */
public class ShowCommand extends Command{


    public ShowCommand(DragonCollection collection, Input input) {
        super(collection, input);
        description = "show - show all the elements of collection";
    }

    /**
     * Method prints each element of collection.
     */
    @Override
    public void execute() {
        if (isInputStreamNotEmpty())
            return;

        drg.getCollection().forEach(System.out::println);
    }

}
