package utilities.commands;

import input.Input;

/**
 * Command "show"
 */
public class ShowCommand extends Command{


    public ShowCommand(Input input) {
        super(input);
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
