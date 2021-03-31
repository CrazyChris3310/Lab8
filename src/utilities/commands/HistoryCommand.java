package utilities.commands;

import input.Input;
import utilities.DragonCollection;

/**
 * Command "history".
 */
public class HistoryCommand extends Command{


    public HistoryCommand(DragonCollection collection, Input input) {
        super(collection, input);
        description = "history - show last 14 commands";
    }

    /**
     * Method shows 14 last commands.
     */
    @Override
    public void execute() {
        if (isInputStreamNotEmpty())
            return;

        drg.showHistory();
    }
}
