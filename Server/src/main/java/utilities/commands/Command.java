package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.io.Serializable;
import java.sql.SQLException;

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
     *
     * @param collection collection to work with.
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
     *
     * @return
     * @param login
     */
    abstract public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException;

    @Override
    public String toString() {
        return name + " - " + description;
    }

}
