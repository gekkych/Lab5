package s466351.lab5.command;

public interface Confirmable {
    default boolean requiresConfirmation() {
        return true;
    }
}
