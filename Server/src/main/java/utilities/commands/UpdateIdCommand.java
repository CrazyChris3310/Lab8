package utilities.commands;

import dragon.Dragon;
import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;

/**
 * Command "update".
 */
public class UpdateIdCommand extends Command {

    private static final long serialVersionUID = 115L;
    Long id;
    Dragon dragon;

    public UpdateIdCommand(DragonCollection collection) {
        super(collection);
        description = "update id {element} - update the element with given id";
    }

    /**
     * Method inputs id and then changes fields of dragon with given id to new ones.
     *
     * @return
     * @param login
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {

//        ZonedDateTime creation = drg.removeFromQueue(id);
//        dragon.setId(id);
//        dragon.setCreationDate(creation);
//        drg.add(dragon);

        drg.updateId(id, dragon,login);

        return new Response();
    }
}
