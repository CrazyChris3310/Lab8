package utilities.commands;

import dragon.Person;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

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
     */
    @Override
    public Response execute() throws NoSuchKillerException {
        drg.removeByKiller(killer);
        return new Response();
    }
}
