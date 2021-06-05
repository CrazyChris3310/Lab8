package input;

import dragon.*;
import exceptions.WrongIdFormatException;
import exceptions.WrongInputFormatException;
import exceptions.WrongPathRightsException;
import utilities.Func;

import java.io.Console;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class {@code Input} defines methods to work with an input from various sources.
 */
abstract public class Input implements Serializable {

    Scanner sc;

    abstract public <T> T input(String question, String errorMessage, Func<T> rule) throws WrongInputFormatException;

    public String inputLogin() {
        System.out.print("Login: ");
        return sc.nextLine().trim();
    }

    public String inputPassword() {
        System.out.print("Password: ");
//        Console console = System.console();
//        char[] password = console.readPassword();
//        return new String(password);
        return sc.nextLine().trim();
    }

    public boolean needRegistration() throws WrongInputFormatException {
        Func<Boolean> interfc = (str) -> {
            if (str.matches("^(su|li)$"))
                switch (str) {
                    case "su": return true;
                    case "li": return false;
                }
            throw new WrongInputFormatException();
        };

        return input("Do you want to sign up or log in? (su/li) ","Wrong answer format!", interfc);
    }


    /**
     * Method asks to input name of dragon.
     * @return name of the dragon.
     */
    public String inputDragonName() throws WrongInputFormatException {

        Func<String> interfc = (str) -> {
            if (str.equals("") || str.equals("\n"))
                throw new WrongInputFormatException();
            return str;
        };

        return input("Enter the name: ", "Wrong name! Try again: ", interfc);
    }

    /**
     * Method asks to input X coordinate.
     * @return dragon's X coordinate.
     */
    public Long inputXCord() throws WrongInputFormatException {
        Func<Long> interfc = (str) -> {
            long res = Long.parseLong(str);
            if (res > 302)
                throw new WrongInputFormatException();
            return res;
        };
        return input("Enter the X coordinate: (x <= 302) ", "Wrong Coordinate! Try again: ", interfc);
    }

    /**
     * Method asks to input Y coordinate.
     * @return dragon's Y coordinate.
     */
    public float inputYCord() throws WrongInputFormatException {
        return input("Enter the Y coordinate: ", "Wrong Y Coordinate! Try again: ", Float::parseFloat);
    }

    /**
     * Method asks to input dragon's age.
     * @return age of the dragon.
     */
    public int inputAge() throws WrongInputFormatException {
        Func<Integer> interfc = (str) -> {
            int res = Integer.parseInt(str);
            if (res <= 0)
                throw new WrongInputFormatException();
            return res;
        };
        return input("Enter the dragon's age: ", "Wrong age Format! Try again: ", interfc);
    }


    /**
     * Method asks to input dragon's description.
     * @return description of the dragon.
     */
    public String inputDescription() throws WrongInputFormatException {
        return input("Enter the dragon's description: ", "", (str) -> str);
    }


    /**
     * Method asks to input dragon's wingspan.
     * @return wingspan of the dragon.
     */
    public Long inputWingspan() throws WrongInputFormatException {
        Func<Long> iterfc = (str) -> {
            if (str.equals(""))
                return null;
            long span = Long.parseLong(str);
            if (span <= 0)
                throw new WrongInputFormatException();
            return span;
        };

        return input("Enter the dragon's wingspan: ", "Wrong wingspan! Try again: ", iterfc);
    }

    /**
     * Method asks to input dragon's type.
     * @return type of dragon.
     */
    public DragonType inputType() throws WrongInputFormatException {
        Func<DragonType> interfc = (str) -> {
            if (str.toUpperCase().matches("AIR|UNDERGROUND|FIRE|WATER")) {
                return DragonType.valueOf(str.toUpperCase());
            }
            else if (str.equals(""))
                return null;
            throw new WrongInputFormatException();
        };
        return input("Enter the dragon's type: (AIR, UNDERGROUND, FIRE, WATER) ",
                "Wrong type format! Try again: ", interfc);
    }

    /**
     * Method asks to input name of a person who killed dragon.
     * @return name of the killer.
     */
    public String inputKillerName() throws WrongInputFormatException {
        Func<String> interfc = (str) -> {
            if (str.equals("") || str.equals("\n"))
                throw new WrongInputFormatException();
            return str;
        };
        return input("Enter the killer's name: ", "Wrong name! Try again: ", interfc);
    }

    /**
     * Method asks to input date of birth of dragon's killer.
     * @return killer's birthday.
     */
    public String inputKilBirthday() throws WrongInputFormatException {
        Func<String> interfc = (str) -> {
            if (str.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) " +
                    "(0[0-9]|1[0-9]|2[0-4]):([0-5]\\d):([0-5]\\d)"))
                return str;
            throw new WrongInputFormatException();
        };
        return input("Enter the killer's birthday: (YYYY-MM-DD hh:mm:ss) ",
                "Wrong date format! Try again: ", interfc);

    }

    /**
     * Method asks to input killer's eye color.
     * @return color of killer's eyes.
     */
    public Color inputKilEyeColor() throws WrongInputFormatException {
        Func<Color> interfc = (str) -> {
            if (str.toUpperCase().matches("WHITE|RED|ORANGE|YELLOW|GREEN|BLACK")) {
                return Color.valueOf(str.toUpperCase());
            }
            throw new WrongInputFormatException();
        };
        return input("Enter the killer's eye color: (WHITE, RED, ORANGE, YELLOW, GREEN, BLACK) ",
                "Wrong color format! Try again: ", interfc);
    }

    /**
     * Method asks to input killer's hair color.
     * @return color of killer's hair.
     */
    public Color inputKilHairColor() throws WrongInputFormatException {
        Func<Color> interfc = (str) -> {
            if (str.toUpperCase().matches("WHITE|RED|ORANGE|YELLOW|GREEN|BLACK")) {
                return Color.valueOf(str.toUpperCase());
            }
            throw new WrongInputFormatException();
        };
        return input("Enter the killer's hair color: (WHITE, RED, ORANGE, YELLOW, GREEN, BLACK) ",
                "Wrong color format! Try again: ", interfc);

    }

    /**
     * Method asks to input Z location of killer.
     * @return Z location.
     */
    public long inputKilZLoc() throws WrongInputFormatException {
        return input("Enter the killer's Z location: ", "Wrong coordinate! Try again: ",
                Long::parseLong);
    }

    /**
     * Method asks to input Y location of killer.
     * @return Y location.
     */
    public Long inputKilYLoc() throws WrongInputFormatException {
        return input("Enter the killer's Y location: ", "Wrong coordinate! Try again: ",
                Long::parseLong);
    }

    /**
     * Method asks to input X location of killer.
     * @return X location.
     */
    public int inputKilXLoc() throws WrongInputFormatException {
        return input("Enter the killer's X location: ", "Wrong coordinate! Try again: ",
                Integer::parseInt);
    }

    /**
     * Method asks to input country where killer was born.
     * @return killer's nation.
     */
    public Country inputKilNation() throws WrongInputFormatException {
        Func<Country> interfc = (str) -> {
            if (str.toUpperCase().matches("ITALY|USA|VATICAN|NORTH_KOREA|INDIA"))
                return Country.valueOf(str.toUpperCase());
            throw new WrongInputFormatException();
        };

        return input("Enter the killer's nationality: (ITALY, NORTH_KOREA, USA, INDIA, VATICAN) ",
                "Wrong country format! Try again: ", interfc);
    }

    /**
     * Method asks whether user needs to input killer or not.
     * @return true if killer defined, false in other case.
     */
    public boolean needKiller() throws WrongInputFormatException {
        Func<Boolean> interfc = (str) -> {
            if (str.matches("[yn]"))
                switch (str) {
                    case "y": return true;
                    case "n": return false;
                }
            throw new WrongInputFormatException();
        };

        return input("Is there a killer? (y/n) ","Wrong answer format! Try again: ", interfc);

    }

    /**
     * Method inputs a dragon with all its attributes.
     * @return dragon.
     * @throws WrongInputFormatException if any attribute has wrong format.
     */
    public Dragon inputDragon() throws WrongInputFormatException {
        return new Dragon(inputDragonName(), new Coordinates(inputXCord(), inputYCord()), inputAge(), inputDescription(),
                inputWingspan(), inputType(), needKiller() ? inputKiller() : null);
    }

    /**
     * Method inputs a killer with all his attributes.
     * @return killer.
     * @throws WrongInputFormatException if any attribute has invalid format.
     */
    public Person inputKiller() throws WrongInputFormatException {
        return new Person(inputKillerName(), inputKilBirthday(), inputKilEyeColor(), inputKilHairColor(),
                inputKilNation(), new Location(inputKilXLoc(), inputKilYLoc(), inputKilZLoc()));

    }

    /**
     * Method inputs path to a file.
     * @return {@code path} scanned from input.
     * @throws WrongInputFormatException if path has wrong format.
     */
    public Path inputFilePath() throws WrongInputFormatException, WrongPathRightsException, InvalidPathException{
        String path;
        path = sc.nextLine().trim();

        if (path.equals(""))
            throw new WrongInputFormatException();
        Path f = Paths.get(path);
        isValidPath(f);

        return f;
    }

    private void isValidPath(Path path) throws WrongPathRightsException {

        if (Files.notExists(path)) {
            throw new WrongPathRightsException("This script file does not exist");
        }
        if (Files.isDirectory(path)) {
            throw new WrongPathRightsException("Path is a directory");
        }
        if (!Files.isReadable(path)) {
            throw new WrongPathRightsException("Can not read file: " + path.getFileName());
        }

    }

    /**
     * Method inputs id from a source.
     * @return scanned id
     * @throws WrongIdFormatException if id has wrong format
     */
    public Long inputId() throws WrongIdFormatException {
        long id;
        if (sc.hasNextLong()) {
            id = sc.nextLong();
            if (sc.nextLine().equals("") && id > 0) {
                return id;
            }
        }
        else
            sc.nextLine();



        throw new WrongIdFormatException("Wrong id format!");
    }

    /**
     * Method scans everything in current line
     * @return the line that was skipped.
     */
    public String nextLine() {
        return sc.nextLine();
    }

    /**
     * Returns true if there is another token in input.
     * @return true if and only if this scanner has another token.
     */
    public boolean hasNext() {
        return sc.hasNext();
    }

    /**
     * Finds and returns the next complete token from this input stream.
     * @return the next token
     */
    public String next() { return sc.next(); }
}
