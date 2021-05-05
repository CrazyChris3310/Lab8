package utilities.commands;

import utilities.DragonCollection;
import utilities.Response;


/**
 * Command "ExecuteCommand". Executes script.
 */
public class ExecuteScriptCommand extends Command{

    private static final long serialVersionUID = 104L;

    public ExecuteScriptCommand(DragonCollection collection) {
        super(collection);
        description = "execute_script file_name - read and execute script from given file";
    }

    /**
     * Method inputs file path to script and executes it if it's valid.
     * @return
     */
    @Override
    public Response execute() {
        return new Response();
    }

}
