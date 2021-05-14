package utilities.commands;

import dragon.Dragon;
import utilities.DragonCollection;
import utilities.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Command "print_field_descending_age".
 */
public class PrintFieldDescendingAgeCommand extends Command {

    private static final long serialVersionUID = 110L;

    public PrintFieldDescendingAgeCommand(DragonCollection collection) {
        super(collection);
        description = "print_field_descending_age - show the age of each element in descending order";
    }

    /**
     * Method prints age field of each element in descending error.
     *
     * @return
     */
    @Override
    public Response execute() {
        return new Response(drg.getCollection().stream()
                .map(Dragon::getAge)
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.toCollection(ArrayList::new)));
    }
}
