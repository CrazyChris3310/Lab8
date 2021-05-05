package utilities.commands;

import dragon.Dragon;
import exceptions.NoSuchIdException;
import utilities.DragonCollection;
import utilities.Response;

import java.time.ZonedDateTime;

/**
 * Command "update".
 */
public class UpdateIdCommand extends Command{

    private static final long serialVersionUID = 115L;
    Long id;
    Dragon dragon;

    public UpdateIdCommand(DragonCollection collection) {
        super(collection);
        description = "update id {element} - update the element with given id";
    }

    /**
     * Method inputs id and then changes fields of dragon with given id to new ones.
     * @return
     */
    @Override
    public Response execute() throws NoSuchIdException {

        ZonedDateTime creation = drg.removeFromQueue(id);
        dragon.setId(id);
        dragon.setCreationDate(creation);
        drg.add(dragon);

        return new Response();
    }
}
