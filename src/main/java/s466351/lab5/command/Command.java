package s466351.lab5.command;

/**
 * Абстрактный класс, представляющий команду с именем, возможностью выхода и подтверждения.
 * Реализует интерфейс {@link Comparable}, чтобы команды можно было сравнивать по имени.
 */
public abstract class Command implements Comparable<Command> {
    private final String name;

    /**
     * Создаёт новую команду.
     *
     * @param name                Название команды.
     */
    public Command(String name) {
        this.name = name;
    }

    /**
     * Запускает выполнение команды с указанным аргументом.
     *
     * @param argument Аргумент команды.
     */
    public abstract String execute(String argument);

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
}