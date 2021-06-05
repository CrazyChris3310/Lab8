package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;


/**
 * Command "ExecuteCommand". Executes script.
 */
public class ExecuteScriptCommand extends Command {

    private static final long serialVersionUID = 104L;

    public ExecuteScriptCommand(DragonCollection collection) {
        super(collection);
        description = "execute_script file_name - read and execute script from given file";
    }

    /**
     * Method inputs file path to script and executes it if it's valid.
     *
     * @param login
     * @return
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
        return new Response();
    }

}
