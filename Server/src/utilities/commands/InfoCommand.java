package utilities.commands;

import utilities.DragonCollection;
import utilities.Response;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Command "info".
 */
public class InfoCommand extends Command{

    private static final long serialVersionUID = 108L;

    public InfoCommand(DragonCollection collection) {
        super(collection);
        description = "info - show information about collection";
    }

    /**
     * Method shows information about collection.
     * @return
     */
    @Override
    public Response execute() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Type of elements: Dragon");
        result.add("Size = " + drg.getSize());
        result.add("Collection type: PriorityQueue");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm:ss");
        result.add("Initialization date: " + fmt.format(drg.getInitDate()));
        result.add("\n");

        return new Response(result);
    }
}
