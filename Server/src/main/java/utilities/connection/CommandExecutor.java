package utilities.connection;

import exceptions.*;
import org.apache.logging.log4j.Logger;
import utilities.DragonCollection;
import utilities.Request;
import utilities.Response;
import utilities.commands.Command;
import utilities.commands.ShowCommand;
import utilities.dataBase.DataBaseConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class CommandExecutor implements Callable<Response> {

    private DragonCollection collection;
    private Logger logger;
    private Request request;
    private ReentrantLock locker;
    DataBaseConnection dbc;

    public CommandExecutor(Request request, DragonCollection drg, Logger logger, ReentrantLock locker, DataBaseConnection dbc) {
        this.request = request;
        this.collection = drg;
        this.logger = logger;
        this.locker = locker;
        this.dbc = dbc;
    }

    @Override
    public Response call() {

        Response response = new Response();

        if (request.getReceivingError() != null) {
            response.setMessage(request.getReceivingError());
            response.setDestination(request.getSource());
            return response;
        }

        try {
            byte[] hash = MessageDigest.getInstance("SHA-512").digest(request.getPassword().getBytes());
            if (request.isRegistering()) {
                dbc.register(request.getLogin(), hash);
            } else {
                dbc.signIn(request.getLogin(), hash);
            }
        } catch (WrongPasswordException e) {
            response.setMessage("Wrong password");
            response.setDestination(request.getSource());
            return response;
        } catch (SQLException e) {
            response.setMessage("Unable to access database " + e.getMessage());
            response.setDestination(request.getSource());
            return response;
        } catch (UserAlreadyExistsException e) {
            response.setMessage("User with such login already exists");
            response.setDestination(request.getSource());
            return response;
        } catch (UserNotExistsException e) {
            response.setMessage("User with such login does not exists");
            response.setDestination(request.getSource());
            return response;
        } catch (NoSuchAlgorithmException e) {
            response.setMessage("Algorithm \"SHA-512\" not found");
            logger.error("Algorithm \"SHA-512\" not found");
            response.setDestination(request.getSource());
            return response;
        }

        response.setSuccessfulConnect(true);

        Command command = request.getCommand();
        if (command == null) {
            response.setMessage(request.getReceivingError());
            response.setDestination(request.getSource());
            return response;
        }

        command.setDrg(collection);

        locker.lock();
        try {
            collection.updateHistory(command.getName());
            response = command.execute(request.getLogin());
        } catch (NoSuchIdException | NoSuchKillerException e) {
            response.setMessage(e.getMessage());
        } catch (SQLException e) {
            response.setMessage("Unable to connect to database" + e.getMessage());
        } catch (NoRightsException e) {
            response.setMessage("You don't have rights to modify this dragon");
        } finally {
            if (!(command instanceof ShowCommand))
                logger.info(command.getName() + " command executed");
            locker.unlock();
        }
        response.setDestination(request.getSource());
        return response;
    }

}
