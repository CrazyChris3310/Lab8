package utilities.connection;

import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import org.apache.logging.log4j.Logger;
import utilities.DragonCollection;
import utilities.Request;
import utilities.Response;
import utilities.commands.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.Callable;

public class CommandExecutor implements Callable<Response> {

    private DragonCollection collection;
    private Logger logger;
    private Request request;

    public CommandExecutor(Request request, DragonCollection drg, Logger logger) {
        this.request = request;
        this.collection = drg;
        this.logger = logger;
    }

    @Override
    public Response call() {

        Response response = new Response();

        Command command = request.getCommand();
        if (command == null) {
            response.setMessage(request.getReceivingError());
            response.setDestination(request.getSource());
            return response;
        }
        command.setDrg(collection);

        try {
            collection.updateHistory(command.getName());
            response = command.execute();
        } catch (NoSuchIdException | NoSuchKillerException e) {
            response.setMessage(e.getMessage());
        }
        logger.info(command.getName() + " command executed");
        response.setDestination(request.getSource());
        return response;
    }
}
