package utilities.commands;

import utilities.DragonCollection;
import utilities.Response;

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
    public Response execute() {
        drg.removeFirst();
        return new Response();
    }
}
