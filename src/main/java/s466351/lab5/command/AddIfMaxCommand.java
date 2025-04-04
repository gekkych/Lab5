package s466351.lab5.command;

import s466351.lab5.exception.MovieCannotBeAddedException;
import s466351.lab5.movie.*;

/**
 * Добавление нового элемента в коллекцию, если его значение {@code oscarCount} максимально в коллекции.
 */
public class AddIfMaxCommand extends Command implements MovieDataReceiver {
    /**
     * Класс для работы с коллекцией.
     */
    private final MovieDeque movies;
    /**
     * Конструктор.
     *
     * @param movies класс для работы с коллекцией.
     */
    public AddIfMaxCommand(MovieDeque movies) {
        super("add_if_max");
        this.movies = movies;
    }

    /**
     * Запрашивает ввод у пользователя, валидирует поля и добавляет элемент в коллекцию, если его значение {@code oscarCount} максимально в коллекции.
     *
     * @param argument Аргумент команды не влияет на выполнение.
     *
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String argument, MovieData data) {
        for(Movie movie : movies.getMovies()) {
            if (data.oscarCount() <= movie.getOscarsCount()) {
                throw new MovieCannotBeAddedException("значение oscarCount не максимально.");
            }
        }
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
        return this.getName() + " - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
