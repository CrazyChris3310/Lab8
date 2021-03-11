package input;

import dragon.*;
import exceptions.WrongInputFormatException;
import utilities.Func;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Class {@code FileInput} defines methods to work with an input from file.
 * If data in file isn't valid {@code WrongInputException} is thrown.
 */
public class FileInput extends Input {

    public FileInput(Path path) throws IOException{
        sc = new Scanner(path);
    }

    @Override
    public <T> T input(String question, String errorMessage, Func<T> rule) throws WrongInputFormatException{
        T result;
        String data = sc.nextLine();
        try {
            result = rule.func(data);
        } catch (NumberFormatException e) {
            throw new WrongInputFormatException();
        }
        return result;
    }

}
