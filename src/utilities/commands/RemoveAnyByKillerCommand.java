package utilities.commands;

import dragon.Person;
import exceptions.NoSuchKillerException;
import exceptions.WrongInputFormatException;
import input.Input;
import utilities.DragonCollection;

/**
 * Command "remove_any_by_killer".
 */
public class RemoveAnyByKillerCommand extends Command{


    public RemoveAnyByKillerCommand(DragonCollection collection, Input input) {
        super(collection, input);
    }

    /**
     * Method inputs killer and removes an element with the same killer from collection.
     */
    @Override
    public void execute() {
        Person killer;
        try {
            input.nextLine();
            killer = input.inputKiller();
        } catch (WrongInputFormatException e) {
            System.out.println("Wrong data!");
            return;
        }

        try {
            drg.removeByKiller(killer);
        } catch (NoSuchKillerException e) {
            System.out.println("No such killer in the collection");
        }

    }
}
