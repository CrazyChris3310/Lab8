package utilities.commands;

import input.ConsoleInput;
import input.Input;

import java.util.HashMap;

/**
 * Command "help".
 */
public class HelpCommand extends Command{

    private static final long serialVersionUID = 106L;
    private HashMap<String, Command> commands;

    public HelpCommand(Input input, HashMap<String, Command> commands) {
        super(input);
        this.commands = commands;
        name = "help";
        description = "show information for available commands";
    }

    public HelpCommand(HashMap<String, Command> commands) {
        this(new ConsoleInput(), commands);
    }

    /**
     * Method shows information about all commands.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();


    }
}
