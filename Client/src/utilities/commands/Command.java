package utilities.commands;

import input.Input;

import java.io.Serializable;

/**
 * Root class for all commands. Contains source for input and the collection to work with.
 */
abstract public class Command implements Serializable {

    transient Input input;
    String description;
    String name;

    /**
     * Constructs command with given input.
     * @param input input source.
     */
    public Command(Input input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    /**
     * Executes command.
     * @return
     */
    abstract public boolean execute();

    @Override
    public String toString() {
        return description;
    }

    boolean isInputStreamNotEmpty() {
        if (input.nextLine().trim().isEmpty()) {
            return false;
        }
        System.out.println("Wrong command format!");
        return true;
    }
}
