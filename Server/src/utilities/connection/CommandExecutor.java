package utilities.connection;

import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import org.apache.logging.log4j.Logger;
import utilities.DragonCollection;
import utilities.Response;
import utilities.commands.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CommandExecutor {

    private DragonCollection collection;
    Logger logger;

    public CommandExecutor(DragonCollection drg, Logger logger) {
        this.collection = drg;
        this.logger = logger;
    }

    public Response execute(byte[] data) throws IOException, ClassNotFoundException {
        Command command;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            command = (Command) ois.readObject();
        }
        command.setDrg(collection);

        Response response = new Response();
        try {
            response = command.execute();
        } catch (NoSuchIdException | NoSuchKillerException e) {
            response.setMessage(e.getMessage());
        }
        logger.info(command.getName() + " command executed");
        return response;
    }
}
