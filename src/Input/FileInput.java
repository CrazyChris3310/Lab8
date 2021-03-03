package Input;

import Dragon.*;
import Exceptions.WrongInputFormatException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Class {@code FileInput} defines methods to work with an input from file.
 * If data in file isn't valid {@code WrongInputException} is thrown.
 */
public class FileInput extends Input {

    /**
     * Constructs input class which source is a file on path.
     * @param path path to file with data.
     * @throws FileNotFoundException if file not found.
     */
    public FileInput(Path path) throws IOException {
        sc = new Scanner(path);
    }

    /**
     * Method reads dragon's name from file.
     * @return name of the dragon.
     * @throws WrongInputFormatException if name has wrong format
     */
    @Override
    public String inputDragonName() throws WrongInputFormatException {
        String dragName = sc.nextLine();
        if (dragName.equals("") || dragName.equals("\n"))
            throw new WrongInputFormatException();
        return dragName;
    }

    /**
     * Methods reads X coordinate of dragon from file.
     * @return X coordinate.
     * @throws WrongInputFormatException if X coordinate isn't valid.
     */
    public Long inputXCord() throws WrongInputFormatException {
        String temp = sc.nextLine();

        if (temp.matches("-[1-9]\\d*|0|[12]\\d{0,2}|3\\d?|30[012]"))
            return Long.parseLong(temp);

        throw new WrongInputFormatException();
    }

    /**
     * Methods reads Y coordinate of dragon from file.
     * @return Y coordinate
     * @throws WrongInputFormatException if Y coordinate isn't valid
     */
    public float inputYCord() throws WrongInputFormatException {
        float cordY;

        if (sc.hasNextFloat()) {
            cordY = sc.nextFloat();
            if (sc.nextLine().equals(""))
                return cordY;
        }

        throw new WrongInputFormatException();
    }

    /**
     * Methods reads dragon's age from file.
     * @return age of dragon.
     * @throws WrongInputFormatException if age isn't valid
     */
    public int inputAge() throws WrongInputFormatException {
        int dragAge;
        String temp = sc.nextLine();
        if (temp.matches("[1-9]\\d*"))
            return Integer.parseInt(temp);

        throw new WrongInputFormatException();
    }

    /**
     * Methods reads dragon's description.
     * @return description od dragon.
     */
    @Override
    public String inputDescription() {
        String description;
        description = sc.nextLine();
        if (description.equals(""))
            description = null;
        return  description;
    }

    /**
     * Methods reads wingspan of the dragon.
     * @return dragon's wingspan.
     * @throws WrongInputFormatException if wingspan has wrong format.
     */
    @Override
    public Long inputWingspan() throws WrongInputFormatException {
        String temp;

        temp = sc.nextLine();
        if (temp.matches("\\d*")) {
            if (temp.equals(""))
                return null;
            else
                return Long.parseLong(temp);
        }

        throw new WrongInputFormatException();
    }

    /**
     * Methods reads type of the dragon from file.
     * @return dragon's type
     * @throws WrongInputFormatException if type is not in {@link DragonType}
     */
    public DragonType inputType() throws WrongInputFormatException {
        String temp = sc.nextLine();
        DragonType type;

        if (temp.toUpperCase().matches("AIR|UNDERGROUND|FIRE|WATER"))
            return DragonType.valueOf(temp.toUpperCase());

        throw new WrongInputFormatException();
    }

    /**
     * Methods reads name of the person who killed dragon.
     * @return killer's name.
     * @throws WrongInputFormatException if name is empty.
     */
    @Override
    public String inputKillerName() throws WrongInputFormatException {
        String personName = sc.nextLine();
        if (personName.equals("") || personName.equals("\n"))
            throw new WrongInputFormatException();
        return personName;
    }

    /**
     * Methods reads date of birth of dragon's killer.
     * @return killer's birthday.
     * @throws WrongInputFormatException if date and time are invalid.
     */
    @Override
    public String inputKilBirthday() throws WrongInputFormatException {
        String date = sc.nextLine();
        if (date.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) " +
                "(0[0-9]|1[0-9]|2[0-4]):([0-5]\\d):([0-5]\\d)"))
            return date;

        throw new WrongInputFormatException();
    }

    /**
     * Methods reads killer's eye color from file.
     * @return eye color of killer
     * @throws WrongInputFormatException if color is not in {@link Color}
     */
    @Override
    public Color inputKilEyeColor() throws WrongInputFormatException {
        String temp;

        temp = sc.nextLine();
        if (temp.toUpperCase().matches("WHITE|RED|ORANGE|YELLOW|GREEN|BLACK")) {
            return Color.valueOf(temp.toUpperCase());
        }

        throw new WrongInputFormatException();
    }

    /**
     * Methods reads killer's hair color from file.
     * @return hair color of killer
     * @throws WrongInputFormatException if color is not in {@link Color}
     */
    @Override
    public Color inputKilHairColor() throws WrongInputFormatException {
        String temp;
        Color hair;

        temp = sc.nextLine();
        if (temp.toUpperCase().matches("WHITE|RED|ORANGE|YELLOW|GREEN|BLACK")) {
            return Color.valueOf(temp.toUpperCase());
        }

    throw new WrongInputFormatException();

    }

    /**
     * Method reads killer's Z location coordinate from file.
     * @return Z coordinate of killer.
     * @throws WrongInputFormatException if Z coordinate is invalid.
     */
    @Override
    public long inputKilZLoc() throws WrongInputFormatException {
        long locZ;

        if (sc.hasNextLong()) {
            locZ = sc.nextLong();
            if (sc.nextLine().equals(""))
                return locZ;
        }

        throw new WrongInputFormatException();
    }

    /**
     * Method reads killer's location Y coordinate from file.
     * @return Y coordinate of killer.
     * @throws WrongInputFormatException if input has wrong input.
     */
    @Override
    public Long inputKilYLoc() throws WrongInputFormatException {
        long locY;

        if(sc.hasNextLong()) {
            locY = sc.nextLong();
            if (sc.nextLine().equals(""))
                return locY;
        }

        throw new WrongInputFormatException();
    }

    /**
     * Method reads killer's location X coordinate from file.
     * @return X coordinate of killer.
     * @throws WrongInputFormatException if data in file has wrong format.
     */
    @Override
    public int inputKilXLoc() throws WrongInputFormatException {
        int locX;

        if (sc.hasNextInt()) {
            locX = sc.nextInt();
            if (sc.nextLine().equals(""))
                return locX;
        }

        throw new WrongInputFormatException();
    }

    /**
     * Method reads country, where killer was born from file.
     * @return killer's nation.
     * @throws WrongInputFormatException if country is not in {@link Country}.
     */
    @Override
    public Country inputKilNation() throws WrongInputFormatException {
        String temp = sc.nextLine();
        if (temp.toUpperCase().matches("ITALY|USA|VATICAN|NORTH_KOREA|INDIA"))
            return Country.valueOf(temp.toUpperCase());

        throw new WrongInputFormatException();
    }

    /**
     * Method reads whether there is a killer next or not.
     * @return true if killer answer is "y" and false if answer is "n".
     * @throws WrongInputFormatException if the answer is not "y" or "n".
     */
    @Override
    public boolean needKiller() throws WrongInputFormatException {
        String ans = sc.nextLine();
        if (ans.matches("y|n"))
            switch(ans) {
                case "y": return true;
                case "n": return false;
            }
        throw new WrongInputFormatException();
    }



}
