package utilities.commands;

import dragon.Dragon;
import exceptions.WrongInputFormatException;
import input.ConsoleInput;
import input.Input;

/**
 * Command "remove_greater".
 */
public class RemoveGreaterCommand extends Command{

    private static final long serialVersionUID = 114L;
    Dragon dragon;

    public RemoveGreaterCommand(Input input) {
        super(input);
        name = "remove_greater {element}";
        description = "remove all elements, that are greater then given element";
    }

    public RemoveGreaterCommand(Dragon dragon) {
        this(new ConsoleInput());
        this.dragon = dragon;
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
