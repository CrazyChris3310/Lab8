package utilities.commands;

import input.Input;

/**
 * Command "show"
 */
public class ShowCommand extends Command{

    private static final long serialVersionUID = 102L;

    public ShowCommand(Input input) {
        super(input);
        name = "show";
        description = "show all the elements of collection";
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
