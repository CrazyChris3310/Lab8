package utilities.commands;

import utilities.DragonCollection;
import utilities.Response;

/**
 * Command "clear". Clears the collection.
 */
public class ClearCommand extends Command {

    private static final long serialVersionUID = 103L;

    public ClearCommand(DragonCollection collection) {
        super(collection);
        description = "clear - remove everything from collection";
    }

    /**
     * Method removes everything from collection.
     *
     * @return
     */
    @Override
    public Response execute() {
        drg.clear();
        return new Response();
    }
}
