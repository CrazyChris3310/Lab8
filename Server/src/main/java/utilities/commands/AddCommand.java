package utilities.commands;

import dragon.Dragon;
import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;
import java.time.ZonedDateTime;

/**
 * Command "Add". Adds element to collection.
 */
public class AddCommand extends Command {

    private static final long serialVersionUID = 101L;
    private Dragon dragon;

    public AddCommand(DragonCollection collection) {
        super(collection);
        description = "add {element} - add new element to collection";
    }

    /**
     * Method inputs a dragon and adds it into collection.
     *
     * @return
     * @param login
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        dragon.setCreationDate(ZonedDateTime.now());
        drg.add(dragon, login);
        return new Response();
    }
}
