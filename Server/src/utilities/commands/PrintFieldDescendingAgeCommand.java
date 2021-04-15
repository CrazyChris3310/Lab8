package utilities.commands;

import dragon.Dragon;
import utilities.DragonCollection;

import java.util.ArrayList;

/**
 * Command "print_field_descending_age".
 */
public class PrintFieldDescendingAgeCommand extends Command{

    private static final long serialVersionUID = 110L;

    public PrintFieldDescendingAgeCommand(DragonCollection collection) {
        super(collection);
        description = "print_field_descending_age - show the age of each element in descending order";
    }

    /**
     * Method prints age field of each element in descending error.
     * @return
     */
    @Override
    public ArrayList<String> execute() {
        ArrayList<String> result;
        return drg.getCollection().stream()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .collect(ArrayList::new, (ls, dr) -> ls.add(String.valueOf(dr.getAge())), ArrayList::addAll);

    }
}
