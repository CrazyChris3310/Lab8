package utilities.commands;

import dragon.Person;
import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;

/**
 * Command "remove_any_by_killer".
 */
public class RemoveAnyByKillerCommand extends Command {

    private static final long serialVersionUID = 111L;
    Person killer;

    public RemoveAnyByKillerCommand(DragonCollection collection) {
        super(collection);
        description = "remove_any_by_killer killer - remove from collection one element with given killer";
    }

    /**
     * Method inputs killer and removes an element with the same killer from collection.
     *
     * @return
     * @param login
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        drg.removeByKiller(killer, login);
        return new Response();
    }
}
