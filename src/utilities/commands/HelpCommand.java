package utilities.commands;

import input.Input;
import utilities.DragonCollection;
import utilities.Process;

import java.util.Map;

/**
 * Command "help".
 */
public class HelpCommand extends Command{


    public HelpCommand(DragonCollection collection, Input input) {
        super(collection, input);
        description = "help - show information for available commands";
    }

    /**
     * Method shows information about all commands.
     */
    @Override
    public void execute() {
        input.nextLine();
        Process proc = new Process(drg, input);
        for (Map.Entry<String, Command> command : proc.getCommands().entrySet()) {
            System.out.println(command.getValue());
        }

    }
}
