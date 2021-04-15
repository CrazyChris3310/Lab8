package utilities.commands;

import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Root class for all commands. Contains source for input and the collection to work with.
 */
abstract public class Command implements Serializable {

    private static final long serialVersionUID = 100L;
    transient DragonCollection drg;
    String description;
    String name;

    /**
     * Constructs command with given collection and input.
     * @param collection collection to work with.
     *
     */
    public Command(DragonCollection collection) {
        drg = collection;
    }

    public void setDrg(DragonCollection drg) {
        this.drg = drg;
    }

    public String getName() {
        return name;
    }

    /**
     * Executes command.
     * @return
     */
    abstract public ArrayList<String> execute() throws NoSuchKillerException, NoSuchIdException;

    @Override
    public String toString() {
        return name + " - " + description;
    }

}
