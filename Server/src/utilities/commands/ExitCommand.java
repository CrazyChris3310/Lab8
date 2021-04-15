package utilities.commands;

import utilities.DragonCollection;
import java.util.ArrayList;

/**
 * Command "exit".
 */
public class ExitCommand extends Command{

    private static final long serialVersionUID = 105L;

    public ExitCommand(DragonCollection collection) {
        super(collection);
        description = "exit - stop the program without saving";
    }

    @Override
    public ArrayList<String> execute() {
        return null;
    }
}
