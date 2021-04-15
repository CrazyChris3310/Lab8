package utilities.commands;

import dragon.Dragon;
import exceptions.WrongInputFormatException;
import input.Input;

/**
 * Command "remove_greater".
 */
public class RemoveGreaterCommand extends Command{

    Dragon dragon;

    public RemoveGreaterCommand(Input input) {
        super(input);
        description = "remove_greater {element} - remove all elements, that are greater then given element";
    }

    /**
     * Method inputs a dragon and removes each dragon that is greater that entered.
     * @return
     */
    @Override
    public boolean execute() {
        if (isInputStreamNotEmpty())
            return false;

        try {
            dragon = input.inputDragon();
        } catch (WrongInputFormatException e) {
            System.out.println("Wrong Data given");
            return false;
        }

        return true;
    }
}
