package input;

import dragon.*;
import exceptions.WrongIdFormatException;
import exceptions.WrongInputFormatException;
import exceptions.WrongPathRightsException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class {@code Input} defines methods to work with an input from various sources.
 */
abstract public class Input{

    Scanner sc;

    /**
     * Input name.
     * @return dragon's name.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public String inputDragonName() throws WrongInputFormatException;

    /**
     * Input X coordinate.
     * @return dragon's X coordinate.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public Long inputXCord() throws WrongInputFormatException;

    /**
     * Input Y coordinate.
     * @return dragon's Y coordinate.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public float inputYCord() throws WrongInputFormatException;

    /**
     * Input age.
     * @return dragon's age.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public int inputAge() throws WrongInputFormatException;

    /**
     * Input description.
     * @return dragon's description.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public String inputDescription() throws WrongInputFormatException;

    /**
     * Input wingspan.
     * @return dragon's wingspan.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public Long inputWingspan() throws WrongInputFormatException;

    /**
     * Input type of the dragon.
     * @return dragon's type.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public DragonType inputType() throws WrongInputFormatException;

    /**
     * Input name.
     * @return killer's name.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public String inputKillerName() throws WrongInputFormatException;

    /**
     * Input date.
     * @return killer's date of birth.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public String inputKilBirthday() throws WrongInputFormatException;

    /**
     * Input eye color.
     * @return killer's eye color.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public Color inputKilEyeColor() throws WrongInputFormatException;

    /**
     * Input hair color.
     * @return killer's hair color.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public Color inputKilHairColor() throws WrongInputFormatException;

    /**
     * Input nationality.
     * @return killer's nationality.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public Country inputKilNation() throws WrongInputFormatException;

    /**
     * Input X location.
     * @return killer's X location.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public int inputKilXLoc() throws WrongInputFormatException;

    /**
     * Input Y location.
     * @return killer's Y location.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public Long inputKilYLoc() throws WrongInputFormatException;

    /**
     * Input Z location.
     * @return killer's Z location.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public long inputKilZLoc() throws WrongInputFormatException;

    /**
     * Define whether the killer is needed.
     * @return true if killer is needed, false otherwise.
     * @throws WrongInputFormatException if data is wrong.
     */
    abstract public boolean needKiller() throws WrongInputFormatException;

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
        path = sc.next();
        if (sc.nextLine().equals("")) {
            isValidPath(path);
            return Paths.get(path);
        }
        throw new WrongInputFormatException();
    }

    private void isValidPath(String a) throws WrongPathRightsException, InvalidPathException{
        Path path = Paths.get(a);

        if (Files.isDirectory(path)) {
            throw new WrongPathRightsException("path is a directory");
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
