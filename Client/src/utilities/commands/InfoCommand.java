package utilities.commands;

import input.Input;

/**
 * Command "info".
 */
public class InfoCommand extends Command{


    public InfoCommand(Input input) {
        super(input);
        description = "info - show information about collection";
    }

    /**
     * Method shows information about collection.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();

//        System.out.println("Type of elements: Dragon");
//        System.out.println("Size = " + drg.getSize());
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm:ss");
//        System.out.println("Initialization date: " + fmt.format(drg.getInitDate()));
//        System.out.println();
    }
}
