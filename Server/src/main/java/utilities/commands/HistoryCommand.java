package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;

/**
 * Command "history".
 */
public class HistoryCommand extends Command {

    private static final long serialVersionUID = 107L;

    public HistoryCommand(DragonCollection collection) {
        super(collection);
        description = "history - show last 14 commands";
    }

    /**
     * Method shows 14 last commands.
     *
     * @return
     * @param login
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        return new Response(drg.getHistory());
    }
}
