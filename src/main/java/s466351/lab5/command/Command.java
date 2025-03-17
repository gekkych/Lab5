package s466351.lab5.command;

/**
 * Абстрактный класс, представляющий команду с именем, возможностью выхода и подтверждения.
 * Реализует интерфейс {@link Comparable}, чтобы команды можно было сравнивать по имени.
 */
public abstract class Command implements Comparable<Command> {
    private final String name;
    private boolean requiresExit;
    private final boolean requiresConfirmation;

    /**
     * Создаёт новую команду.
     *
     * @param name                Название команды.
     * @param requiresConfirmation Требуется ли подтверждение перед выполнением команды.
     */
    public Command(String name, boolean requiresConfirmation) {
        this.name = name;
        this.requiresExit = false;
        this.requiresConfirmation = requiresConfirmation;
    }

    /**
     * Запускает выполнение команды с указанным аргументом.
     *
     * @param argument Аргумент команды.
     */
    public abstract void start(String argument);

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    public abstract String description();

    /**
     * Сравнивает команды по имени для сортировки.
     *
     * @param command Другая команда для сравнения.
     * @return Отрицательное число, ноль или положительное число, если данная команда
     *         меньше, равна или больше переданной команды.
     */
    @Override
    public int compareTo(Command command) {
        return this.getName().compareTo(command.getName());
    }

    /**
     * Возвращает название команды.
     *
     * @return Название команды.
     */
    public String getName() {
        return name;
    }

    /**
     * Проверяет, требует ли команда завершения программы.
     *
     * @return {@code true}, если команда завершает выполнение программы, иначе {@code false}.
     */
    public boolean isRequiresExit() {
        return requiresExit;
    }

    /**
     * Устанавливает значение {@code requiresExit} на {@code true}.
     */
    public void requireExit() {
        requiresExit = true;
    }

    /**
     * Проверяет, требуется ли подтверждение перед выполнением команды.
     *
     * @return {@code true}, если требуется подтверждение, иначе {@code false}.
     */
    public boolean isRequiresConfirmation() {
        return requiresConfirmation;
    }
}