package utilities.commands;

import dragon.Person;
import exceptions.WrongInputFormatException;
import input.Input;

/**
 * Command "remove_any_by_killer".
 */
public class RemoveAnyByKillerCommand extends Command{

    Person killer;

    public RemoveAnyByKillerCommand(Input input) {
        super(input);
        description = "remove_any_by_killer killer - remove from collection one element with given killer";
    }

    /**
     * Method inputs killer and removes an element with the same killer from collection.
     * @return
     */
    @Override
    public boolean execute() {
        if (isInputStreamNotEmpty())
            return false;

        try {
            killer = input.inputKiller();
        } catch (WrongInputFormatException e) {
            System.out.println("Wrong data!");
            return false;
        }

        return true;
    }
}
