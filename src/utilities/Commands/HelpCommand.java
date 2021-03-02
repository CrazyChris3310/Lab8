package utilities.Commands;

import Input.Input;
import utilities.DragonCollection;

/**
 * Command "help".
 */
public class HelpCommand extends Command{


    public HelpCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    /**
     * Method shows information about all commands.
     */
    @Override
    public void execute() {
        System.out.println("help - show information for available commands");
        System.out.println("info - show information about collection");
        System.out.println("show - show all the elements of collection");
        System.out.println("add {element} - add new element to collection");
        System.out.println("update id {element} - update the element with given id");
        System.out.println("remove_by_id id - remove element with given id");
        System.out.println("clear - remove everything from collection");
        System.out.println("save - save collection to the file");
        System.out.println("execute_script file_name - read and execute script from given file");
        System.out.println("exit - stop the program without saving");
        System.out.println("remove_first - remove the first element from collection");
        System.out.println("remove_greater {element} - remove all elements, that are greater then given element");
        System.out.println("history - show last 14 commands");
        System.out.println("remove_any_by_killer killer - remove from collection one element with given killer");
        System.out.println("print_descending - show elements in descending order");
        System.out.println("print_field_descending_age - show the age of each element in descending order");
    }
}
