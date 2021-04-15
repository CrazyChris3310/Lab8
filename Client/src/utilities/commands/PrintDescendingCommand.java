package utilities.commands;

import input.Input;

/**
 * Command "print_descending".
 */
public class PrintDescendingCommand extends Command{


    public PrintDescendingCommand(Input input) {
        super(input);
        description = "print_descending - show elements in descending order";
    }

    /**
     * Method prints elements of collection in descending order.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();
//
//        ArrayList<Dragon> temp = new ArrayList<>(drg.getCollection());
//        temp.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
//        temp.forEach(System.out::println);
    }
}
