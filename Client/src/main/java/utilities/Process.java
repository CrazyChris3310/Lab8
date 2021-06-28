package utilities;

import dragon.Dragon;
import exceptions.ServerUnavailableException;
import gui.MainFrame;
import utilities.commands.*;
import input.*;

import javax.swing.*;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Class that is responsible for defining command and input source.
 */
public class Process {

    private HashMap<String, Command> commands = new HashMap<>();
    private Input input;
    private ConnectionManager connectionManager;

    private MainFrame mainFrame;

    private String login;
    private String password;

    /**
     * Constructs a {@code Process} with given collection and input source.
     * Also defines all the commands available.
     *
     * @param source input stream.
     */
    public Process(Input source, ConnectionManager cm) {
        input = source;
        connectionManager = cm;
        commands.put("help", new HelpCommand(input, commands));
        commands.put("add", new AddCommand(input));
        commands.put("info", new InfoCommand(input));
        commands.put("clear", new ClearCommand(input));
        commands.put("execute_script", new ExecuteScriptCommand(input, connectionManager, this));
        commands.put("print_descending", new PrintDescendingCommand(input));
        commands.put("history", new HistoryCommand(input));
        commands.put("print_field_descending_age", new PrintFieldDescendingAgeCommand(input));
        commands.put("remove_any_by_killer", new RemoveAnyByKillerCommand(input));
        commands.put("remove_by_id", new RemoveByIdCommand(input));
        commands.put("remove_first", new RemoveFirstCommand(input));
        commands.put("remove_greater", new RemoveGreaterCommand(input));
        commands.put("show", new ShowCommand(input));
        commands.put("update", new UpdateIdCommand(input));
        commands.put("exit", new ExitCommand(input));
//        collection = new ArrayList<>();
    }

    public Process(Input source, ConnectionManager cm, String login, String password) {
        this(source, cm);
        this.login = login;
        this.password = password;
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    /**
     * Method defines commands from console input and executes them.
     */
    public void startProcess() {

        String command;

        while (true) {

            System.out.print("Input a command: ");
            try {
                command = input.next();
            } catch (NoSuchElementException e) {
                System.exit(0);
                return;
            }

            if (!commands.containsKey(command)) {
                System.out.println("Wrong command!");
                continue;
            }

            defineCommand(command);
        }
    }

    public void defineCommand(String command) {
        Command com = commands.get(command);
        if (!com.execute())
            return;
        sendAndExecute(com);
    }

    // TODO: show message dialogs instead od usual console output
    public void sendAndExecute(Command com) {

        try {
            connectionManager.connect();
        } catch (ServerUnavailableException e) {
            System.out.println("Server is temporarily unavailable");
            if (com instanceof ExitCommand) {
                System.exit(0);
            }
            return;
        }

        Request request = new Request(login, password);
        request.setCommand(com);

        try {
            connectionManager.send(request);
        } catch (SocketException e) {
            return;
        } catch (IOException e) {
            System.out.println("IOException happened. Unable to send data");
            e.printStackTrace();
            return;
        }

        try {
            Response response = connectionManager.receive();
            processResult(response);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (IOException e) {
            System.out.println("IOException happened while receiving data");
        }
    }

    public String authorize(boolean register, String login, String password){
        Response response;

        Request request = new Request(login, password);
        request.setRegistering(register);

        try {
            connectionManager.connect();
        } catch (ServerUnavailableException e) {
            return "Server is temporarily unavailable";
        }

        try {
            connectionManager.send(request);
        } catch (SocketException e) {
            return "Socket Exception happened";
        } catch (IOException e) {
            return "IOException happened. Unable to send data. ";
        }

        try {
            response = connectionManager.receive();
        } catch (ClassNotFoundException e) {
            return "Class not found";
        } catch (IOException e) {
            return "IOException happened while receiving data";
        }

        if (response.isSuccessfulConnect()) {
            return null;
        } else {
            return response.getMessage();
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
                defineCommand(command);
            } else {
                System.out.println("Wrong command!");
                break;
            }
        }
    }

    // FIXME: make Dialog frames open from GUI
    private void processResult(Response response) {
        mainFrame.informationReceived(response);
//        if (response.getToExit()) {
//            System.exit(0);
//        } else if (response.getCollection() != null) {
//            collection = response.getCollection();
//
//        } else if (response.getAnswer() != null) {
//            StringBuilder builder = new StringBuilder();
//            for (String str: response.getAnswer()) {
//                builder.append(str).append("\n");
//            }
//            JOptionPane.showMessageDialog(null, builder.toString());
//        } else {
//            System.out.println(response.getMessage());
//        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public ArrayList<Dragon> getCollection() {
//        return collection;
//    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}
