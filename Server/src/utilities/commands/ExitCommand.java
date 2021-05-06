package utilities.commands;

import utilities.DragonCollection;
import utilities.Response;

/**
 * Command "exit".
 */
public class ExitCommand extends Command{

    private static final long serialVersionUID = 105L;

    public ExitCommand(DragonCollection collection) {
        super(collection);
        description = "exit - stop the program without saving";
    }

    @Override
    public Response execute() {
        System.out.println("Client has disconnected from your server");
        SaveCommand save = new SaveCommand(drg);
        save.execute();
        Response response = new Response();
        response.setToExit(true);
        return response;
    }
}
