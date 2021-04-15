package utilities.commands;

import utilities.DragonCollection;
import java.util.ArrayList;

/**
 * Command "print_descending".
 */
public class PrintDescendingCommand extends Command{

    private static final long serialVersionUID = 109L;

    public PrintDescendingCommand(DragonCollection collection) {
        super(collection);
        description = "print_descending - show elements in descending order";
    }

    /**
     * Method prints elements of collection in descending order.
     * @return
     */
    @Override
    public ArrayList<String> execute() {
        return drg.getCollection().stream()
                .sorted((o1, o2) -> -o2.compareTo(o1))
                .collect(ArrayList::new, (ls, o) -> ls.add(o.toString()), ArrayList::addAll);
    }
}
