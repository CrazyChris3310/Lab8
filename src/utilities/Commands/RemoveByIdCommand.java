package utilities.Commands;

import Exceptions.IdException;
import Input.Input;
import utilities.DragonCollection;

/**
 * Command "Remove_by_id".
 */
public class RemoveByIdCommand extends Command{


    public RemoveByIdCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    /**
     * Method inputs id and removes an element with the same id from collection.
     */
    @Override
    public void execute() {
        Long id;
        try {
            id = input.inputId();
            drg.removeFromQueue(id);
        } catch (IdException e) {
            System.out.println(e.getMessage());
        }
    }
}
