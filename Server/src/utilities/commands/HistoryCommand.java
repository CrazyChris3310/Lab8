package utilities.commands;

import utilities.DragonCollection;
import java.util.ArrayList;

/**
 * Command "history".
 */
public class HistoryCommand extends Command{

    private static final long serialVersionUID = 107L;

    public HistoryCommand(DragonCollection collection) {
        super(collection);
        description = "history - show last 14 commands";
    }

    /**
     * Method shows 14 last commands.
     * @return
     */
    @Override
    public ArrayList<String> execute() {
        return drg.getHistory();
    }
}
