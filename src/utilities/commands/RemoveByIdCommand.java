package utilities.commands;

import exceptions.IdException;
import input.Input;
import utilities.DragonCollection;

/**
 * Command "Remove_by_id".
 */
public class RemoveByIdCommand extends Command{


    public RemoveByIdCommand(DragonCollection collection, Input input) {
        super(collection, input);
        description = "remove_by_id id - remove element with given id";
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
