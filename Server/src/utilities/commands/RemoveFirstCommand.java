package utilities.commands;

import utilities.DragonCollection;

import java.util.ArrayList;

/**
 * Command "remove_first".
 */
public class RemoveFirstCommand extends Command{

    private static final long serialVersionUID = 113L;

    public RemoveFirstCommand(DragonCollection collection) {
        super(collection);
        description = "remove_first - remove the first element from collection";
    }

    /**
     * Method removes first element from collection.
     * @return
     */
    @Override
    public ArrayList<String> execute() {
        drg.removeFirst();
        return null;
    }
}
