package utilities.commands;

import utilities.DragonCollection;
import utilities.Response;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Command "help".
 */
public class HelpCommand extends Command{

    private static final long serialVersionUID = 106L;
    HashMap<String, Command> commands;

    public HelpCommand(DragonCollection collection) {
        super(collection);
        description = "help - show information for available commands";
    }

    /**
     * Method shows information about all commands.
     * @return
     */
    @Override
    public Response execute() {
        return new Response(commands.entrySet().stream()
                .collect(ArrayList::new, (ls, es) -> ls.add(es.getValue().toString()), ArrayList::addAll));
    }
}
