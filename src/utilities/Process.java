package utilities;

import utilities.Commands.*;
import Input.*;

import java.util.HashMap;

/**
 * Class that is responsible for defining command and input source.
 */
public class Process {

    private DragonCollection dragons;
    private HashMap<String, Command> commands = new HashMap<>();
    Input input;

    /**
     * Constructs a {@code Process} with given collection and input source.
     * Also defines all the commands available.
     * @param drg given collection.
     * @param source input stream.
     */
    public Process(DragonCollection drg, Input source) {
        dragons = drg;
        input = source;
        commands.put("help", new HelpCommand(dragons, input));
        commands.put("add", new AddCommand(dragons, input));
        commands.put("info", new InfoCommand(dragons, input));
        commands.put("clear", new ClearCommand(dragons, input));
        commands.put("execute_script", new ExecuteScriptCommand(dragons, input));
        commands.put("print_descending", new PrintDescendingCommand(dragons, input));
        commands.put("history", new HistoryCommand(dragons, input));
        commands.put("print_field_descending_age", new PrintFieldDescendingAgeCommand(dragons, input));
        commands.put("remove_any_by_killer", new RemoveAnyByKillerCommand(dragons, input));
        commands.put("remove_by_id", new RemoveByIdCommand(dragons, input));
        commands.put("remove_first", new RemoveFirstCommand(dragons, input));
        commands.put("remove_greater", new RemoveGreaterCommand(dragons, input));
        commands.put("save", new SaveCommand(dragons, input));
        commands.put("show", new ShowCommand(dragons, input));
        commands.put("update", new UpdateIdCommand(dragons, input));
        commands.put("exit", new ExitCommand(dragons, input));
    }

    /**
     * Method defines commands from console input and executes them.
     */
    public void defineCommand() {
        String command;
        do {
            System.out.print("Input a command: ");
            command = input.next();

            if (commands.containsKey(command)) {
                dragons.updateHistory(command);
                commands.get(command).execute();
            }
            else {
                System.out.println("Wrong command!");
            }

        } while(!command.equals("exit"));
    }

    /**
     * Method defines commands from file input and executes them.
     */
    public void defineFileCommand() {
        String command;
        while (input.hasNext()) {
            command = input.next();

            if (commands.containsKey(command)) {
                commands.get(command).execute();
            }
            else {
                System.out.println("Wrong command!");
                break;
            }

        }
    }

}
