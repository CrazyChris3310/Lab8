package input;

import dragon.*;
import exceptions.WrongInputFormatException;
import utilities.Func;

import java.util.Scanner;

/**
 * Class {@code ConsoleInput} defines methods to work with an input from console.
 * Each method has a validation of User's input.
 */
public class ConsoleInput extends Input{

    /**
     * Constructs input class with console input as a source.
     */
    public ConsoleInput() {
        sc = new Scanner(System.in);
    }


    /**
     * Method asks to input name of dragon.
     * @return name of the dragon.
     */
    @Override
    public String inputDragonName() {

        Func<String> interfc = (str) -> {
            if (str.equals("") || str.equals("\n"))
                throw new WrongInputFormatException();
            return str;
        };

        System.out.print("Enter the name: ");
        return input("Wrong name! Try again: ", interfc);
    }

    /**
     * Method asks to input X coordinate.
     * @return dragon's X coordinate.
     */
    public Long inputXCord() {
        System.out.print("Enter the X coordinate: (x <= 302) ");

        Func<Long> interfc = (str) -> {
            long res = Long.parseLong(str);
            if (res > 302)
                throw new WrongInputFormatException();
            return res;
        };

        return input("Wrong Coordinate! Try again: ", interfc);
    }

    /**
     * Method asks to input Y coordinate.
     * @return dragon's Y coordinate.
     */
    @Override
    public float inputYCord() {
        System.out.print("Enter the Y coordinate: ");

        return input("Wrong Y Coordinate! Try again: ", Float::parseFloat);
    }

    /**
     * Method asks to input dragon's age.
     * @return age of the dragon.
     */
    public int inputAge() {

        System.out.print("Enter the dragon's age: ");

        Func<Integer> interfc = (str) -> {
            int res = Integer.parseInt(str);
            if (res <= 0)
                throw new WrongInputFormatException();
            return res;
        };
        return input("Wrong age Format! Try again: ", interfc);
    }


    /**
     * Method asks to input dragon's description.
     * @return description of the dragon.
     */
    public String inputDescription() {

        System.out.print("Enter the dragon's description: ");
        return input("", (str) -> str);
    }


    /**
     * Method asks to input dragon's wingspan.
     * @return wingspan of the dragon.
     */
    @Override
    public Long inputWingspan() {

        System.out.print("Enter the dragon's wingspan: ");

        Func<Long> iterfc = (str) -> {
            if (str.equals(""))
                return null;
            long span = Long.parseLong(str);
            if (span <= 0)
                throw new WrongInputFormatException();
            return span;
        };

        return input("Wrong wingspan! Try again: ", iterfc);
    }

    /**
     * Method asks to input dragon's type.
     * @return type of dragon.
     */
    @Override
    public DragonType inputType() {

        System.out.print("Enter the dragon's type: (AIR, UNDERGROUND, FIRE, WATER) ");
        Func<DragonType> interfc = (str) -> {
            if (str.toUpperCase().matches("AIR|UNDERGROUND|FIRE|WATER")) {
                return DragonType.valueOf(str.toUpperCase());
            }
            throw new WrongInputFormatException();
        };
        return input("Wrong type format! Try again: ", interfc);
    }

    /**
     * Method asks to input name of a person who killed dragon.
     * @return name of the killer.
     */
    @Override
    public String inputKillerName() {

        System.out.print("Enter the killer's name: ");

        Func<String> interfc = (str) -> {
            if (str.equals("") || str.equals("\n"))
                throw new WrongInputFormatException();
            return str;
        };
        return input("Wrong name! Try again: ", interfc);
    }

    /**
     * Method asks to input date of birth of dragon's killer.
     * @return killer's birthday.
     */
    @Override
    public String inputKilBirthday() {
        System.out.print("Enter the killer's birthday: (YYYY-MM-DD hh:mm:ss) ");

        Func<String> interfc = (str) -> {
            if (str.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) " +
                    "(0[0-9]|1[0-9]|2[0-4]):([0-5]\\d):([0-5]\\d)"))
                return str;
            throw new WrongInputFormatException();
        };
        return input("Wrong date format! Try again: ", interfc);

    }

    /**
     * Method asks to input killer's eye color.
     * @return color of killer's eyes.
     */
    @Override
    public Color inputKilEyeColor() {

        System.out.print("Enter the killer's eye color: (WHITE, RED, ORANGE, YELLOW, GREEN, BLACK) ");

        Func<Color> interfc = (str) -> {
            if (str.toUpperCase().matches("WHITE|RED|ORANGE|YELLOW|GREEN|BLACK")) {
                return Color.valueOf(str.toUpperCase());
            }
            throw new WrongInputFormatException();
        };
        return input("Wrong color format! Try again: ", interfc);
    }

    /**
     * Method asks to input killer's hair color.
     * @return color of killer's hair.
     */
    @Override
    public Color inputKilHairColor() {

        System.out.print("Enter the killer's hair color: (WHITE, RED, ORANGE, YELLOW, GREEN, BLACK) ");

        Func<Color> interfc = (str) -> {
            if (str.toUpperCase().matches("WHITE|RED|ORANGE|YELLOW|GREEN|BLACK")) {
                return Color.valueOf(str.toUpperCase());
            }
            throw new WrongInputFormatException();
        };
        return input("Wrong color format! Try again: ", interfc);

    }

    /**
     * Method asks to input Z location of killer.
     * @return Z location.
     */
    @Override
    public long inputKilZLoc() {

        System.out.print("Enter the killer's Z location: ");
        return input("Wrong coordinate! Try again: ", Long::parseLong);
    }

    /**
     * Method asks to input Y location of killer.
     * @return Y location.
     */
    @Override
    public Long inputKilYLoc() {

        System.out.print("Enter the killer's Y location: ");
        return input("Wrong coordinate! Try again: ", Long::parseLong);


    }

    /**
     * Method asks to input X location of killer.
     * @return X location.
     */
    @Override
    public int inputKilXLoc() {

        System.out.print("Enter the killer's X location: ");
        return input("Wrong coordinate! Try again: ", Integer::parseInt);
    }

    /**
     * Method asks to input country where killer was born.
     * @return killer's nation.
     */
    @Override
    public Country inputKilNation() {

        System.out.print("Enter the killer's nationality: (ITALY, NORTH_KOREA, USA, INDIA, VATICAN) ");

        Func<Country> interfc = (str) -> {
          if (str.toUpperCase().matches("ITALY|USA|VATICAN|NORTH_KOREA|INDIA"))
              return Country.valueOf(str.toUpperCase());
          throw new WrongInputFormatException();
        };

        return input("Wrong country format! Try again: ", interfc);
    }

    /**
     * Method asks whether user needs to input killer or not.
     * @return true if killer defined, false in other case.
     */
    @Override
    public boolean needKiller() {

        System.out.print("Is there a killer? (y/n) ");

        Func<Boolean> interfc = (str) -> {
            if (str.matches("[yn]"))
                switch (str) {
                    case "y": return true;
                    case "n": return false;
                }
            throw new WrongInputFormatException();
        };

        return input("Wrong answer format! Try again: ", interfc);

    }

    public<T> T input(String message, Func<T> rule) {
        T result;
        while (true) {
            String data = sc.nextLine();
            try {
                result = rule.func(data);
            } catch (NumberFormatException | WrongInputFormatException e) {
                System.out.print(message);
                continue;
            }
            return result;
        }
    }

}
