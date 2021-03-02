package utilities.Commands;

import Input.Input;
import utilities.DragonCollection;

/**
 * Command "show"
 */
public class ShowCommand extends Command{


    public ShowCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    /**
     * Method prints each element of collection.
     */
    @Override
    public void execute() {
        drg.getCollection().forEach(System.out::println);
    }

}
