package utilities.commands;

import utilities.DragonCollection;
import java.util.ArrayList;

/**
 * Command "clear". Clears the collection.
 */
public class ClearCommand extends Command{

    private static final long serialVersionUID = 103L;

    public ClearCommand(DragonCollection collection) {
        super(collection);
        description = "clear - remove everything from collection";
    }

    /**
     * Method removes everything from collection.
     * @return
     */
    @Override
    public ArrayList<String> execute() {
        drg.clear();
        return null;
    }
}
