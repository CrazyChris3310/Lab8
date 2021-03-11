package utilities.commands;

import dragon.Dragon;
import input.Input;
import utilities.DragonCollection;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Command "save".
 */
public class SaveCommand extends Command{


    public SaveCommand(DragonCollection collection, Input input) {
        super(collection, input);
        description = "save - save collection to the file";
    }

    /**
     * Method writes elements of collection into file in csv format.
     */
    @Override
    public void execute() {
        DateTimeFormatter zdtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss xxxxx");
        DateTimeFormatter ldtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        String output;

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(drg.getFile()))) {
            for (Dragon dragon : drg.getCollection()) {
                output = "" + dragon.getId() + ",\"" + dragon.getName().replaceAll("\"", "\"\"")
                        + "\"," + dragon.getCoordinates().getX() + "," + dragon.getCoordinates().getY() + "," +
                        zdtFormatter.format(dragon.getCreationDate()) + "," + dragon.getAge() + ",\"" +
                        defineNull(dragon.getDescription()).replaceAll("\"", "\"\"") +
                        "\"," + defineNull(dragon.getWingspan()) + "," + defineNull(dragon.getType()) + ",";
                if (dragon.getKiller() == null)
                    output += ",,,,,,,\n";
                else
                    output += "\"" + dragon.getKiller().getName().replaceAll("\"", "\"\"") + "\","
                            + ldtFormatter.format(dragon.getKiller().getBirthday()) + "," +
                            dragon.getKiller().getEyeColor() + "," + dragon.getKiller().getHairColor() +
                            "," + dragon.getKiller().getNationality() + "," + dragon.getKiller().getLocation().getX() +
                            "," + dragon.getKiller().getLocation().getY() + "," + dragon.getKiller().getLocation().getZ()
                            + "\n";

                bos.write(output.getBytes());
                bos.flush();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!)");
        } catch (IOException e) {
            System.out.println("Writing error");
        }

    }

    /**
     * Method defines what to write to file if object is null.
     * @param str given object.
     * @param <T> type of given object.
     * @return if str is null, then "", in other case str.
     */
    public <T> String defineNull(T str) {
        if (str == null)
            return "";
        return str.toString();
    }
}
