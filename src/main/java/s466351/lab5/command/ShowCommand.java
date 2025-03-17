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
        super("show", false);
        this.movieDeque = movieDeque;
    }

    /**
     * Проверяет наличие элементов в коллекции и выводит строковое представление каждого элемента.
     * @param argument Аргумент команды, не влияет на выполнение.
     */
    @Override
    public void start(String argument) {
        if (movieDeque.getMovies().isEmpty()) {
            System.out.println("Пустая коллекция");
        }
        for(Movie movie : movieDeque.getMovies()) {
            System.out.println(movie.toString());
        }
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
