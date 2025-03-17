package s466351.lab5.exception;

/**
 * Общее исключение для всех исключений связанных с MovieDeque.
 */
public class MovieDequeException extends RuntimeException {
    public MovieDequeException(String description) {
        super("Ошибка в MovieDeque. " + description);
    }
}
