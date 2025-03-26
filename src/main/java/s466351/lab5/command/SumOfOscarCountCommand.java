package s466351.lab5.command;


import s466351.lab5.movie.Movie;
import s466351.lab5.movie.MovieDeque;

/**
 * Выводит сумму премий Оскар всех элементов коллекции.
 */
public class SumOfOscarCountCommand extends Command{
    /**
     * Класс для работы с коллекцией.
     */
    private final MovieDeque movies;

    /**
     * Конструктор.
     *
     * @param movies класс для работы с коллекцией.
     */
    public SumOfOscarCountCommand(MovieDeque movies) {
        super("sum_of_oscar_count");
        this.movies = movies;
    }

    /**
     * Складывает значение {@code oscarCount} всех элементов и выводит его.
     *
     * @param argument Аргумент команды, не влияет на выполнение.
     *
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String argument) {
        int oscarSum = 0;
        for (Movie movie : movies.getMovies()) {
            oscarSum += movie.getOscarsCount();
        }
        return "Сумма всех оскаров " + oscarSum;
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - сумма всех оскаров";
    }
}
