package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

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
        Response response = new Response();
        response.setCollection(drg.getCollection().stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new)));
        return response;
    }
}
