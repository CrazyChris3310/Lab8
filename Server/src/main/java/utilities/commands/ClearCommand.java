package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;

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
     * @param login
     * @return
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        drg.clear(login);
        return new Response();
    }
}
