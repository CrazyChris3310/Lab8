package utilities.commands;

import exceptions.IdException;
import input.Input;

/**
 * Command "Remove_by_id".
 */
public class RemoveByIdCommand extends Command{

    Long id;

    public RemoveByIdCommand(Input input) {
        super(input);
        description = "remove_by_id id - remove element with given id";
    }

    /**
     * Method inputs id and removes an element with the same id from collection.
     * @return
     */
    @Override
    public boolean execute() {
        try {
            id = input.inputId();
        } catch (IdException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
