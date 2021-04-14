package utilities.commands;

import input.Input;
import utilities.ConnectionManager;
import utilities.DragonCollection;

/**
 * Command "show"
 */
public class ShowCommand extends Command{


    public ShowCommand(Input input, ConnectionManager cm) {
        super(input, cm);
        description = "show - show all the elements of collection";
    }

    /**
     * Method prints each element of collection.
     */
    @Override
    public void execute() {
        if (isInputStreamNotEmpty())
            return;

//        drg.getCollection().forEach(System.out::println);
    }

}
