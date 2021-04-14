package utilities;

import utilities.commands.*;
import input.*;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Class that is responsible for defining command and input source.
 */
public class Process {

    private HashMap<String, Command> commands = new HashMap<>();
    Input input;

    /**
     * Constructs a {@code Process} with given collection and input source.
     * Also defines all the commands available.
     * @param source input stream.
     */
    public Process(Input source, ConnectionManager cm) {
        input = source;
        commands.put("help", new HelpCommand(input, cm));
        commands.put("add", new AddCommand(input, cm));
        commands.put("info", new InfoCommand(input, cm));
        commands.put("clear", new ClearCommand(input, cm));
        commands.put("execute_script", new ExecuteScriptCommand(input, cm));
        commands.put("print_descending", new PrintDescendingCommand(input, cm));
        commands.put("history", new HistoryCommand(input, cm));
        commands.put("print_field_descending_age", new PrintFieldDescendingAgeCommand(input, cm));
        commands.put("remove_any_by_killer", new RemoveAnyByKillerCommand(input, cm));
        commands.put("remove_by_id", new RemoveByIdCommand(input, cm));
        commands.put("remove_first", new RemoveFirstCommand(input, cm));
        commands.put("remove_greater", new RemoveGreaterCommand(input, cm));
        commands.put("save", new SaveCommand(input, cm));
        commands.put("show", new ShowCommand(input, cm));
        commands.put("update", new UpdateIdCommand(input, cm));
        commands.put("exit", new ExitCommand(input, cm));
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    /**
     * Method defines commands from console input and executes them.
     */
    public void defineCommand() {
        String command;
        while (true) {
            System.out.print("Input a command: ");
            try {
                command = input.next();
            } catch (NoSuchElementException e) {
                System.exit(0);
                return;
            }

            if (commands.containsKey(command)) {
                commands.get(command).execute();
            }
            else {
                System.out.println("Wrong command!");
            }
        }
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
