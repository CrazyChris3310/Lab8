package utilities.commands;

import input.Input;

/**
 * Command "Remove_by_id".
 */
public class RemoveByIdCommand extends Command{


    public RemoveByIdCommand(Input input) {
        super(input);
        description = "remove_by_id id - remove element with given id";
    }

    /**
     * Method inputs id and removes an element with the same id from collection.
     */
    @Override
    public void execute() {
        Long id;
//        try {
//            id = input.inputId();
//            drg.removeFromQueue(id);
//        } catch (IdException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
