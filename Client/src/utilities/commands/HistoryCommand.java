package utilities.commands;

import input.Input;

/**
 * Command "history".
 */
public class HistoryCommand extends Command{


    public HistoryCommand(Input input) {
        super(input);
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
