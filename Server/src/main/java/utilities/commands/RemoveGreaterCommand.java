package utilities.commands;

import dragon.Dragon;
import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;

/**
 * Command "remove_greater".
 */
public class RemoveGreaterCommand extends Command {

    private static final long serialVersionUID = 114L;
    Dragon dragon;

    public RemoveGreaterCommand(DragonCollection collection) {
        super(collection);
        description = "remove_greater {element} - remove all elements, that are greater then given element";
    }

    /**
     * Method inputs a dragon and removes each dragon that is greater that entered.
     *
     * @param login
     * @return
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        drg.removeGreater(dragon, login);
        return new Response();
    }
}
