package s466351.lab5.exception;

public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super("Ошибка при исполнении команды. " + message);
    }
}
