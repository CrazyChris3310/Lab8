package utilities.commands;

import input.Input;

/**
 * Command "help".
 */
public class HelpCommand extends Command{


    public HelpCommand(Input input) {
        super(input);
        description = "help - show information for available commands";
    }

    /**
     * Method shows information about all commands.
     * @return
     */
    @Override
    public boolean execute() {
        return !isInputStreamNotEmpty();

//        Process proc = new Process(drg, input);
//        for (Map.Entry<String, Command> command : proc.getCommands().entrySet()) {
//            System.out.println(command.getValue());
//        }

    }
}
