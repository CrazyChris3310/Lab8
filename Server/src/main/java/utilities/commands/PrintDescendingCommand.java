package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Command "print_descending".
 */
public class PrintDescendingCommand extends Command {

    private static final long serialVersionUID = 109L;

    public PrintDescendingCommand(DragonCollection collection) {
        super(collection);
        description = "print_descending - show elements in descending order";
    }

    /**
     * Method prints elements of collection in descending order.
     *
     * @param login
     * @return
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        return new Response(drg.getCollection().stream()
                .sorted(Comparator.reverseOrder())
                .collect(ArrayList::new, (ls, o) -> ls.add(o.toString()), ArrayList::addAll));
    }
}
