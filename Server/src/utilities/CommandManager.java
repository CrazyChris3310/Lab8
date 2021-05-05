package utilities;

import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.commands.Command;

import java.io.*;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CommandManager {

    ConnectionManager cManager;
    DragonCollection dragons;

    static Logger LOGGER;

    static {
        try(FileInputStream ins = new FileInputStream("D:\\IdeaProjects\\Lab6\\Server\\Files\\config.txt")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(ConnectionManager.class.getName());
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
    }

    public CommandManager(ConnectionManager cm, DragonCollection drg) {
        cManager = cm;
        dragons = drg;
    }

    public void startProcess() {
        LOGGER.info("Server has started at " + cManager.getLocalAddress());
        while (true) {
            LOGGER.info("Waiting for a connection");
            cManager.connect();
            LOGGER.info("User at " + cManager.getRemoteAddress() + " has connected");
            startExchange();
        }
    }

    public void startExchange() {
        while (true) {
            Command command;
            Response response = new Response();
            String message = null;
            try {
                command = cManager.receiveCommand();
                LOGGER.info(command.getName() + " command received");
                command.setDrg(dragons);
                dragons.updateHistory(command.getName());
                response = command.execute();
                LOGGER.info(command.getName() + " command executed");
            } catch (SocketException e) {
                LOGGER.warning("Client is disconnected");
                return;
            } catch (IOException e) {
                message = "IOException has happened";
                LOGGER.log(Level.WARNING, message, e);
            } catch (ClassNotFoundException e) {
                message = "Class not found";
                LOGGER.log(Level.WARNING, message, e);
            } catch (NoSuchIdException | NoSuchKillerException e) {
                message = e.getMessage();
                LOGGER.log(Level.WARNING, e.getMessage(), e);
            }

            try {
                response.setMessage(message);
                cManager.send(response);
            } catch (SocketException e) {
                LOGGER.warning("Client is disconnected");
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, e.getMessage(), e);
            }
        }

    }


}
