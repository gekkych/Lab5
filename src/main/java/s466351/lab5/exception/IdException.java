package s466351.lab5.exception;

public class IdException extends MovieDequeException {
    public IdException(String message) {
        super("Ошибка с ID. " + message);
    }
}
