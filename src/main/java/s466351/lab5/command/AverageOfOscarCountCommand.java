package s466351.lab5.command;


import s466351.lab5.movie.Movie;
import s466351.lab5.movie.MovieDeque;

/**
 * Выводит среднее количество премий Оскаров у всех фильмов из коллекции.
 */
public class AverageOfOscarCountCommand extends Command {
    /**
     * Класс для управления коллекцией
     */
    private final MovieDeque movies;

    /**
     * Конструктор
     *
     * @param movies класс для управления коллекцией.
     */
    public AverageOfOscarCountCommand(MovieDeque movies) {
        super("average_of_oscar_count");
        this.movies = movies;
    }

    /**
     * Суммирует количество оскаров каждого фильма и выводит среднее значение.
     * @param argument Аргумент команды.
     *
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String argument) {
        if (movies.getMovies().isEmpty()) {
            return "Коллекция фильмов пуста";
        }
        double oscarSum = 0;
        for (Movie movie : movies.getMovies()) {
            oscarSum += movie.getOscarsCount();
        }
        double average = oscarSum / movies.getMovies().size();
        return String.format("Среднее количество оскаров %.2f%n", average);
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - среднее количество оскаров";
    }
}
