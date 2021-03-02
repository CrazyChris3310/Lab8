package Exceptions;

/**
 * Thrown by various methods if given id does not exist.
 */
public class NoSuchIdException extends IdException{

    /**
     * Constructs {@code NoSuchIdException} with the given message string.
     * @param s the detailed message.
     */
    public NoSuchIdException(String s) {
        super(s);
    }
}
