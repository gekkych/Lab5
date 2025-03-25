package s466351.lab5.exception;

/**
 * Общее исключение для всех исключений связанных с MovieDeque.
 */
public class MovieDequeException extends RuntimeException {
    private static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public MovieDequeException(String description) {
        super(ANSI_RED + "Ошибка в MovieDeque. " + description + ANSI_RESET);
    }
}
