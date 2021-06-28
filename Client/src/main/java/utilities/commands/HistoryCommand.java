package utilities.commands;

import input.ConsoleInput;
import input.Input;

/**
 * Command "history".
 */
public class HistoryCommand extends Command{

    private static final long serialVersionUID = 107L;

    public HistoryCommand(Input input) {
        super(input);
        name = "history";
        description = "show last 14 commands";
    }

    public HistoryCommand() {
        this(new ConsoleInput());
    }

    /**
     * Method shows 14 last commands.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();
    }
}
