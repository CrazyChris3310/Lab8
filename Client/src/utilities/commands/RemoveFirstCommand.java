package utilities.commands;

import input.Input;

/**
 * Command "remove_first".
 */
public class RemoveFirstCommand extends Command{


    public RemoveFirstCommand(Input input) {
        super(input);
        description = "remove_first - remove the first element from collection";
    }

    /**
     * Method removes first element from collection.
     */
    @Override
    public void execute() {
        if (isInputStreamNotEmpty())
            return;

//        drg.removeFirst();
    }
}
