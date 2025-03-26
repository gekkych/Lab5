package s466351.lab5.command;



import s466351.lab5.movie.Movie;
import s466351.lab5.movie.MovieDeque;

/**
 * Выводит информацию об элементах коллекции.
 */
public class ShowCommand extends Command {
    /**
     * Класс для работы с коллекцией.
     */
    MovieDeque movieDeque;

    /**
     * Конструктор.
     *
     * @param movieDeque класс для работы с коллекцией.
     */
    public ShowCommand(MovieDeque movieDeque) {
        super("show");
        this.movieDeque = movieDeque;
    }

    /**
     * Проверяет наличие элементов в коллекции и выводит строковое представление каждого элемента.
     * @param argument Аргумент команды, не влияет на выполнение.
     *
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String argument) {
        StringBuilder result = new StringBuilder();
        if (movieDeque.getMovies().isEmpty()) {
            return "Пустая коллекция";
        }
        for(Movie movie : movieDeque.getMovies()) {
            result.append(movie.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - список элементов коллекции";
    }
}
