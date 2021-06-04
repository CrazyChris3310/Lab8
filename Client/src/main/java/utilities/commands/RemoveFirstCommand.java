package utilities.commands;

import input.Input;

/**
 * Command "remove_first".
 */
public class RemoveFirstCommand extends Command{

    private static final long serialVersionUID = 113L;

    public RemoveFirstCommand(Input input) {
        super(input);
        name = "remove_first";
        description = "remove the first element from collection";
    }

    /**
     * Method removes first element from collection.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();
    }
}
