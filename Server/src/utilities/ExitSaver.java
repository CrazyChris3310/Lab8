package utilities;

import utilities.commands.SaveCommand;

public class ExitSaver implements Runnable{

    DragonCollection dragons;

    public ExitSaver(DragonCollection collection) {
        dragons = collection;
    }

    @Override
    public void run() {
        SaveCommand save = new SaveCommand(dragons);
        save.execute();
        System.out.println("The server has successfully finished");
    }
}
