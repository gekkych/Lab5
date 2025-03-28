package s466351.lab5.command;

import s466351.lab5.movie.*;

/**
 * Добавление нового элемента в коллекцию.
 */
public class AddCommand extends Command implements MovieDataReceiver {
    /**
     * Класс для работы с коллекцией.
     */
    private final MovieDeque movies;
    /**
     * Конструктор.
     *
     * @param movies класс для работы с коллекцией.
     */
    public AddCommand(MovieDeque movies) {
        super("add");
        this.movies = movies;
    }

    /**
     * Запрашивает ввод у пользователя, валидирует поля и добавляет элемент в коллекцию.
     *
     * @param argument Аргумент команды не влияет на выполнение.
     *
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String argument, MovieData data) {
        movies.add(data);
        return "Фильм успешно добавлен в коллекцию.";
    }

    @Override
    public String execute(String argument) {
        return "Нужно использовать execute(String, MovieData)";
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - добавить новый элемент в коллекцию";
    }
}
