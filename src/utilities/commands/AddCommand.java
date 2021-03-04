package utilities.commands;

import dragon.*;
import exceptions.WrongInputFormatException;
import input.Input;
import utilities.DragonCollection;

/**
 * Command "Add". Adds element to collection.
 */
public class AddCommand extends Command{


    public AddCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    /**
     * Method inputs a dragon and adds it into collection.
     */
    @Override
    public void execute() {
        try {
            input.nextLine();
            Dragon dragon = input.inputDragon();
            drg.add(dragon);
        } catch (WrongInputFormatException e) {
            System.out.println("Wrong Data given!");
        }
    }
}
