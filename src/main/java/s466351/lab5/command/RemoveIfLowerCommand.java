package s466351.lab5.command;

import s466351.lab5.MovieFieldInput;
import s466351.lab5.movie.*;

import java.util.Iterator;

/**
 * Удаляет все элементы коллекции меньше заданного.\
 * <br> Требует подтверждения.
 */
public class RemoveIfLowerCommand extends Command implements Confirmable {
    /**
     * Класс для работы с коллекцией фильмов.
     */
    private final MovieDeque movies;

    /**
     * Конструктор.
     *
     * @param movies класс для работы с коллекцией фильмов.
     */
    public RemoveIfLowerCommand(MovieDeque movies) {
        super("remove_if_lower");
        this.movies = movies;
    }

    /**
     * Получает ввод от пользователя и сравнивает значения полей введённого и имеющихся элементов.
     * <br> Удаляет все, что меньше.
     *
     * @param argument аргумент команды, не влияет на выполнение.
     *
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String argument) {
        MovieData data = MovieFieldInput.inputMovieData();
        StringBuilder result = new StringBuilder();

        Iterator<Movie> iterator = movies.getMovies().iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (data.oscarCount() > movie.getOscarsCount()) {
                result.append("Удалён фильм ").append(movie.getTitle()).append(" с айди ").append(movie.getId());
                iterator.remove();
            }
        }
        if (result.isEmpty()) {
            return "Фильмов меньше данного не найдено";
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
        return this.getName() + " - удаляет все элементы коллекции, которые меньше заданного";
    }
}
