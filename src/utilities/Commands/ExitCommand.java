package utilities.Commands;

import Input.Input;
import utilities.DragonCollection;

/**
 * Command "exit".
 */
public class ExitCommand extends Command{


    public ExitCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    @Override
    public void execute() {}
}
