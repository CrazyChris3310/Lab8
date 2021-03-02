package utilities.Commands;

import Input.Input;
import utilities.DragonCollection;

/**
 * Command "history".
 */
public class HistoryCommand extends Command{


    public HistoryCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    /**
     * Method shows 14 last commands.
     */
    @Override
    public void execute() {
        drg.showHistory();
    }
}
