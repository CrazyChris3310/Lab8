package utilities.Commands;

import Dragon.Dragon;
import Input.Input;
import utilities.DragonCollection;

import java.util.ArrayList;

/**
 * Command "print_field_descending_age".
 */
public class PrintFieldDescendingAgeCommand extends Command{


    public PrintFieldDescendingAgeCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    /**
     * Method prints age field of each element in descending error.
     */
    @Override
    public void execute() {
        ArrayList<Dragon> temp = new ArrayList<>(drg.getCollection());
        temp.sort((o1, o2) -> o1.getAge() - o2.getAge());
        for (Dragon dragon : temp) {
            System.out.println(dragon.getAge());
        }
    }
}
