package utilities.commands;

import input.Input;

/**
 * Command "print_field_descending_age".
 */
public class PrintFieldDescendingAgeCommand extends Command{

    private static final long serialVersionUID = 110L;

    public PrintFieldDescendingAgeCommand(Input input) {
        super(input);
        name = "print_field_descending_age";
        description = "show the age of each element in descending order";
    }

    /**
     * Method prints age field of each element in descending error.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();
    }
}
