package s466351.lab5.exception;

public class InvalidCommandArgumentException extends CommandException {
    public InvalidCommandArgumentException(String cause) {
        super("Неверное значение аргумента команды: " + cause);
    }
}
