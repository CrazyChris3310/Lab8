package utilities.commands;

import input.Input;
import utilities.ConnectionManager;
import utilities.DragonCollection;

/**
 * Command "history".
 */
public class HistoryCommand extends Command{


    public HistoryCommand(Input input, ConnectionManager cm) {
        super(input, cm);
        description = "history - show last 14 commands";
    }

    /**
     * Method shows 14 last commands.
     */
    @Override
    public void execute() {
        if (isInputStreamNotEmpty())
            return;

//        drg.showHistory();
    }
}
