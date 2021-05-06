package utilities;

import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.commands.Command;

import java.io.*;
import java.net.SocketException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandManager {

    ConnectionManager cManager;
    DragonCollection dragons;

    private static Logger LOGGER = LogManager.getLogger();

    public CommandManager(ConnectionManager cm, DragonCollection drg) {
        cManager = cm;
        dragons = drg;
    }

    public void startProcess() {
        LOGGER.info("Server has started at " + cManager.getLocalAddress());
        while (true) {
            cManager.connect();
            LOGGER.info("User at " + cManager.getRemoteAddress() + " has connected");
            startExchange();
        }
    }

    public void startExchange() {
        Command command;
        Response response = new Response();
        String message = "";
        try {
            command = cManager.receiveCommand();
            LOGGER.info(command.getName() + " command received");
            command.setDrg(dragons);
            dragons.updateHistory(command.getName());
            response = command.execute();
            LOGGER.info(command.getName() + " command executed");
        } catch (SocketException e) {
            LOGGER.warn("Client is disconnected");
            return;
        } catch (IOException e) {
            message = "IOException has happened";
            LOGGER.warn(message, e);
        } catch (ClassNotFoundException e) {
            message = "Class not found";
            LOGGER.warn(message, e);
        } catch (NoSuchIdException | NoSuchKillerException e) {
            message = e.getMessage();
            LOGGER.warn(e.getMessage());
        }

        try {
            response.setMessage(message);
            cManager.send(response);
        } catch (SocketException e) {
            LOGGER.warn("Client is disconnected");
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }


}
