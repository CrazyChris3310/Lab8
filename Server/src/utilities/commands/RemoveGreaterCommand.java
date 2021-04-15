package utilities.commands;

import dragon.Dragon;
import utilities.DragonCollection;

import java.util.ArrayList;

/**
 * Command "remove_greater".
 */
public class RemoveGreaterCommand extends Command{

    private static final long serialVersionUID = 114L;
    Dragon dragon;

    public RemoveGreaterCommand(DragonCollection collection) {
        super(collection);
        description = "remove_greater {element} - remove all elements, that are greater then given element";
    }

    /**
     * Method inputs a dragon and removes each dragon that is greater that entered.
     * @return
     */
    @Override
    public ArrayList<String> execute() {
        drg.removeGreater(dragon);
        return null;
    }
}
