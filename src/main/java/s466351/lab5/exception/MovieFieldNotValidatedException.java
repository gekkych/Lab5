package s466351.lab5.exception;

public class MovieFieldNotValidatedException extends MovieDequeException {
    /**
     * Ошибка при не успешной валидации переменной
     * @param message описание ошибки
     */
    public MovieFieldNotValidatedException(String cause) {
        super("Значение не валидировано: " + cause);
    }
}
