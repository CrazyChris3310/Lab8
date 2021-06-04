package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;

/**
 * Command "Remove_by_id".
 */
public class RemoveByIdCommand extends Command {

    private static final long serialVersionUID = 112L;
    Long id;

    public RemoveByIdCommand(DragonCollection collection) {
        super(collection);
        description = "remove_by_id id - remove element with given id";
    }

    /**
     * Method inputs id and removes an element with the same id from collection.
     *
     * @return
     * @param login
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        drg.removeFromQueue(id, login);
        return new Response();
    }
}
