package s466351.lab5.command;

public interface Closable {
    default boolean requiresClose() {
        return true;
    }
}
