package utilities.commands;

import input.Input;
import utilities.ConnectionManager;
import utilities.DragonCollection;

import java.time.format.DateTimeFormatter;

/**
 * Command "info".
 */
public class InfoCommand extends Command{


    public InfoCommand(Input input, ConnectionManager cm) {
        super(input, cm);
        description = "info - show information about collection";
    }

    /**
     * Method shows information about collection.
     */
    @Override
    public void execute() {
        if (isInputStreamNotEmpty())
            return;

//        System.out.println("Type of elements: Dragon");
//        System.out.println("Size = " + drg.getSize());
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm:ss");
//        System.out.println("Initialization date: " + fmt.format(drg.getInitDate()));
//        System.out.println();
    }
}
