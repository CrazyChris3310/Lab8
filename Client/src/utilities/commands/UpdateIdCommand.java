package utilities.commands;

import input.Input;

/**
 * Command "update".
 */
public class UpdateIdCommand extends Command{

    public UpdateIdCommand(Input input) {
        super(input);
        description = "update id {element} - update the element with given id";
    }

    /**
     * Method inputs id and then changes fields of dragon with given id to new ones.
     */
    @Override
    public void execute() {
//        Long id;
//        try {
//            id = input.inputId();
//            ZonedDateTime creation = drg.removeFromQueue(id);
//            Dragon dragon = input.inputDragon();
//            dragon.setId(id);
//            dragon.setCreationDate(creation);
//            drg.add(dragon);
//        } catch (IdException e) {
//            System.out.println(e.getMessage());
//        } catch (WrongInputFormatException e) {
//            System.out.println("Wrong Data given!");
//        }
    }
}
