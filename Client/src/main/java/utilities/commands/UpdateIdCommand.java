package utilities.commands;

import dragon.Dragon;
import exceptions.IdException;
import exceptions.WrongInputFormatException;
import input.ConsoleInput;
import input.Input;

/**
 * Command "update".
 */
public class UpdateIdCommand extends Command{

    private static final long serialVersionUID = 115L;
    Long id;
    Dragon dragon;

    public UpdateIdCommand(Input input) {
        super(input);
        name = "update id {element}";
        description = "update the element with given id";
    }

    public UpdateIdCommand(Long id, Dragon dragon) {
        this(new ConsoleInput());
        this.id = id;
        this.dragon = dragon;
    }

    /**
     * Method inputs id and then changes fields of dragon with given id to new ones.
     * @return
     */
    @Override
    public boolean execute() {
        try {
            id = input.inputId();
            dragon = input.inputDragon();
        } catch (IdException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (WrongInputFormatException e) {
            System.out.println("Wrong data in the file");
            return false;
        }
        return true;
    }
}
