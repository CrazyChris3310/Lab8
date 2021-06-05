package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Command "help".
 */
public class HelpCommand extends Command {

    private static final long serialVersionUID = 106L;
    private HashMap<String, Command> commands;

    public HelpCommand(DragonCollection collection) {
        super(collection);
        description = "help - show information for available commands";
    }

    /**
     * Method shows information about all commands.
     *
     * @param login
     * @return
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        return new Response(commands.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(ArrayList::new, (ls, es) -> ls.add(es.getValue().toString()), ArrayList::addAll));
    }
}
