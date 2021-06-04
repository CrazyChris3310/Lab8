package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;

/**
 * Command "exit".
 */
public class ExitCommand extends Command {

    private static final long serialVersionUID = 105L;

    public ExitCommand(DragonCollection collection) {
        super(collection);
        description = "exit - stop the program without saving";
    }

    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        Response response = new Response();
        response.setToExit(true);
        return response;
    }
}
