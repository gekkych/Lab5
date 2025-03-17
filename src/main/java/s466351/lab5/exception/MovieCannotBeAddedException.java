package s466351.lab5.exception;

public class MovieCannotBeAddedException extends MovieDequeException {
    public MovieCannotBeAddedException(String cause) {
        super("Фильм не может быть добавлен: " + cause);
    }
}
