package utilities.commands;

import utilities.DragonCollection;
import utilities.Response;

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
     * @return
     */
    @Override
    public Response execute() {
        return new Response(drg.getCollection().stream()
                .sorted(Comparator.reverseOrder())
                .collect(ArrayList::new, (ls, o) -> ls.add(o.toString()), ArrayList::addAll));
    }
}
