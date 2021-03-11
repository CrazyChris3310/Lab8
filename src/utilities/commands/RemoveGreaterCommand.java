package utilities.commands;

import dragon.Dragon;
import exceptions.WrongInputFormatException;
import input.Input;
import utilities.DragonCollection;

/**
 * Command "remove_greater".
 */
public class RemoveGreaterCommand extends Command{


    public RemoveGreaterCommand(DragonCollection collection, Input input) {
        super(collection, input);
        description = "remove_greater {element} - remove all elements, that are greater then given element";
    }

    /**
     * Method inputs a dragon and removes each dragon that is greater that entered.
     */
    @Override
    public void execute() {
        try {
            input.nextLine();
            Dragon dragon = input.inputDragon();
            drg.removeGreater(dragon);
        } catch (WrongInputFormatException e) {
            System.out.println("Wrong Data given");
        }
    }
}
