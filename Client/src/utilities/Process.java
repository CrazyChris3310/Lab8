package utilities;

import utilities.commands.*;
import input.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Class that is responsible for defining command and input source.
 */
public class Process {

    private HashMap<String, Command> commands = new HashMap<>();
    Input input;
    ConnectionManager connectionManager;

    /**
     * Constructs a {@code Process} with given collection and input source.
     * Also defines all the commands available.
     * @param source input stream.
     */
    public Process(Input source, ConnectionManager cm) {
        input = source;
        connectionManager = cm;
        commands.put("help", new HelpCommand(input));
        commands.put("add", new AddCommand(input));
        commands.put("info", new InfoCommand(input));
        commands.put("clear", new ClearCommand(input));
        commands.put("execute_script", new ExecuteScriptCommand(input, connectionManager));
        commands.put("print_descending", new PrintDescendingCommand(input));
        commands.put("history", new HistoryCommand(input));
        commands.put("print_field_descending_age", new PrintFieldDescendingAgeCommand(input));
        commands.put("remove_any_by_killer", new RemoveAnyByKillerCommand(input));
        commands.put("remove_by_id", new RemoveByIdCommand(input));
        commands.put("remove_first", new RemoveFirstCommand(input));
        commands.put("remove_greater", new RemoveGreaterCommand(input));
        commands.put("save", new SaveCommand(input));
        commands.put("show", new ShowCommand(input));
        commands.put("update", new UpdateIdCommand(input));
        commands.put("exit", new ExitCommand(input));
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
                Command com = commands.get(command);
                if(!com.execute())
                    continue;
                connectionManager.send(com);
                CommandStatus status = connectionManager.receiveStatus();
                processResult(status);
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

    public void processResult(CommandStatus stat) {
        if (stat == CommandStatus.FAILED) {
            String message = connectionManager.receiveErrorMessage();
            System.out.println(message);
        }
        else if (stat == CommandStatus.EXECUTED) {
            ArrayList<String> result = connectionManager.receiveResult();
            result.forEach(System.out::println);
        }
    }

}
