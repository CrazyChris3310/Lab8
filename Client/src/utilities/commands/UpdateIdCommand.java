package utilities.commands;

import exceptions.IdException;
import exceptions.WrongInputFormatException;
import input.Input;

/**
 * Command "update".
 */
public class UpdateIdCommand extends Command{

    Long id;

    public UpdateIdCommand(Input input) {
        super(input);
        description = "update id {element} - update the element with given id";
    }

    /**
     * Method inputs id and then changes fields of dragon with given id to new ones.
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
