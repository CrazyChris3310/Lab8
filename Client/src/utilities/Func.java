package utilities;

import exceptions.WrongInputFormatException;

public interface Func<T> {
    T func(String str) throws WrongInputFormatException;
}
