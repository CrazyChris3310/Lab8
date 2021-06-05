package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;

/**
 * Command "remove_first".
 */
public class RemoveFirstCommand extends Command {

    private static final long serialVersionUID = 113L;

    public RemoveFirstCommand(DragonCollection collection) {
        super(collection);
        description = "remove_first - remove the first element from collection";
    }

    /**
     * Method removes first element from collection.
     *
     * @param login
     * @return
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        drg.removeFirst(login);
        return new Response();
    }
}
