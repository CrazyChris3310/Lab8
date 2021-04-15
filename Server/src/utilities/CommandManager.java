package utilities;

import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.commands.Command;

import java.util.ArrayList;

public class CommandManager {

    ConnectionManager cManager;
    DragonCollection dragons;

    public CommandManager(ConnectionManager cm, DragonCollection drg) {
        cManager = cm;
        dragons = drg;
    }

    public void startProcess() {
        while (true) {
            Command command = cManager.receiveCommand();
            command.setDrg(dragons);
            dragons.updateHistory(command.getName());
            ArrayList<String> result;
            try {
                result = command.execute();
                cManager.send(CommandStatus.EXECUTED);
                cManager.send(result);
            } catch (NoSuchIdException | NoSuchKillerException e) {
                cManager.send(CommandStatus.FAILED);
                cManager.send(e.getMessage());
            }
        }
    }
}
