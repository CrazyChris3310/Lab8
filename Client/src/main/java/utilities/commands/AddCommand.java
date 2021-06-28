package utilities.commands;

import dragon.Dragon;
import exceptions.WrongInputFormatException;
import input.ConsoleInput;
import input.Input;

/**
 * Command "Add". Adds element to collection.
 */
public class AddCommand extends Command {

    private static final long serialVersionUID = 101L;
    private Dragon dragon;

    public AddCommand(Input input) {
        super(input);
        name = "add {element}";
        description = "add new element to collection";
    }

    public AddCommand(Dragon dragon) {
        this(new ConsoleInput());
        this.dragon = dragon;
    }

    /**
     * Method inputs a dragon and adds it into collection.
     * @return
     */
    @Override
    public boolean execute() {
        try {
            if (isInputStreamNotEmpty())
                return false;
            dragon = input.inputDragon();
        } catch (WrongInputFormatException e) {
            System.out.println("Wrong Data given!");
            return false;
        }
        return true;
    }
}
