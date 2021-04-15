package utilities.commands;

import exceptions.IdException;
import exceptions.NoSuchIdException;
import utilities.DragonCollection;

import java.util.ArrayList;

/**
 * Command "Remove_by_id".
 */
public class RemoveByIdCommand extends Command{

    private static final long serialVersionUID = 112L;
    Long id;

    public RemoveByIdCommand(DragonCollection collection) {
        super(collection);
        description = "remove_by_id id - remove element with given id";
    }

    /**
     * Method inputs id and removes an element with the same id from collection.
     * @return
     */
    @Override
    public ArrayList<String> execute() throws NoSuchIdException {
        drg.removeFromQueue(id);
        return null;
    }
}
