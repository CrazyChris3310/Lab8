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
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();
    }

}
