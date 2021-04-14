package utilities.commands;

import input.Input;
import utilities.DragonCollection;

import java.io.Serializable;

/**
 * Root class for all commands. Contains source for input and the collection to work with.
 */
abstract public class Command implements Serializable {

    transient DragonCollection drg;
    transient Input input;
    String description;
    String name;

    /**
     * Constructs command with given collection and input.
     * @param collection collection to work with.
     * @param input input source.
     */
    public Command(DragonCollection collection, Input input) {
        drg = collection;
        this.input = input;
    }

    public Command(Input input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    /**
     * Executes command.
     */
    abstract public void execute();

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
