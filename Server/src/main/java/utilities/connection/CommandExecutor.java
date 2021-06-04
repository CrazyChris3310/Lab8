package utilities.connection;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import exceptions.WrongPasswordException;
import org.apache.logging.log4j.Logger;
import utilities.DragonCollection;
import utilities.Request;
import utilities.Response;
import utilities.commands.Command;
import utilities.dataBase.DataBaseConnection;

import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class CommandExecutor implements Callable<Response> {

    private DragonCollection collection;
    private Logger logger;
    private Request request;
    private ReentrantLock locker;

    public CommandExecutor(Request request, DragonCollection drg, Logger logger) {
        this.request = request;
        this.collection = drg;
        this.logger = logger;
        locker = new ReentrantLock();
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

        try {
            DataBaseConnection connection = DataBaseConnection.getInstance();
            if (connection.userExists(request.getLogin())) {
                connection.signIn(request.getLogin(), request.getPassword());
            } else {
                connection.register(request.getLogin(), request.getPassword());
            }
        } catch (WrongPasswordException e) {
            response.setMessage("Wrong password");
            response.setDestination(request.getSource());
            return response;
        } catch (SQLException e) {
            response.setMessage("Unable to access database");
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
        }
        finally {
            logger.info(command.getName() + " command executed");
            locker.unlock();
        }
        response.setDestination(request.getSource());
        return response;
    }

}
