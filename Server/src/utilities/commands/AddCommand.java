package utilities.commands;

import dragon.Dragon;
import utilities.DragonCollection;
import utilities.Response;

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
     */
    @Override
    public Response execute() {
        drg.add(dragon);
        return new Response();
    }
}
