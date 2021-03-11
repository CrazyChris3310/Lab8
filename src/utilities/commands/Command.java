package utilities.commands;
import input.*;
import utilities.*;

/**
 * Root class for all commands. Contains source for input and the collection to work with.
 */
abstract public class Command {

    DragonCollection drg;
    Input input;
    String description;

    /**
     * Constructs command with given collection and input.
     * @param collection collection to work with.
     * @param input input source.
     */
    public Command(DragonCollection collection, Input input) {
        drg = collection;
        this.input = input;
    }

    /**
     * Executes command.
     */
    abstract public void execute();

    @Override
    public String toString() {
        return description;
    }
}
