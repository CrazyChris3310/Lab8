package utilities.commands;

import utilities.DragonCollection;
import utilities.Response;

import java.util.ArrayList;

/**
 * Command "show"
 */
public class ShowCommand extends Command{

    private static final long serialVersionUID = 102L;

    public ShowCommand(DragonCollection collection) {
        super(collection);
        description = "show - show all the elements of collection";
    }

    /**
     * Method prints each element of collection.
     * @return
     */
    @Override
    public Response execute() {
        return new Response(drg.getCollection().stream()
                .collect(ArrayList::new, (ls, dr) -> ls.add(dr.toString()), ArrayList::addAll));
    }

}
