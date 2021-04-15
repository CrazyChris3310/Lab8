package utilities.commands;

import exceptions.IdException;
import input.Input;

/**
 * Command "Remove_by_id".
 */
public class RemoveByIdCommand extends Command{

    private static final long serialVersionUID = 112L;
    Long id;

    public RemoveByIdCommand(Input input) {
        super(input);
        name = "remove_by_id id";
        description = "remove element with given id";
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
