package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Command "show"
 */
public class ShowCommand extends Command {

    private static final long serialVersionUID = 102L;

    public ShowCommand(DragonCollection collection) {
        super(collection);
        description = "show - show all the elements of collection";
    }

    /**
     * Method prints each element of collection.
     *
     * @return
     * @param login
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        return new Response(drg.getCollection().stream()
                .sorted()
                .collect(ArrayList::new, (ls, dr) -> ls.add(dr.toString()), ArrayList::addAll));
    }

}
