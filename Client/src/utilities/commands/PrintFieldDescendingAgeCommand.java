package utilities.commands;

import dragon.Dragon;
import input.Input;
import utilities.ConnectionManager;
import utilities.DragonCollection;

import java.util.ArrayList;

/**
 * Command "print_field_descending_age".
 */
public class PrintFieldDescendingAgeCommand extends Command{


    public PrintFieldDescendingAgeCommand(Input input, ConnectionManager cm) {
        super(input, cm);
        description = "print_field_descending_age - show the age of each element in descending order";
    }

    /**
     * Method prints age field of each element in descending error.
     */
    @Override
    public void execute() {
        if (isInputStreamNotEmpty())
            return;
//
//        ArrayList<Dragon> temp = new ArrayList<>(drg.getCollection());
//        temp.sort((o1, o2) -> o2.getAge() - o1.getAge());
//        for (Dragon dragon : temp) {
//            System.out.println(dragon.getAge());
//        }
    }
}
