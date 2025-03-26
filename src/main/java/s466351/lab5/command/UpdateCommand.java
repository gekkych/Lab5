package s466351.lab5.command;

import s466351.lab5.MovieFieldInput;
import s466351.lab5.exception.InvalidCommandArgumentException;
import s466351.lab5.movie.*;

/**
 * Команда для обновления информации о фильме в коллекции по его ID.
 */
public class UpdateCommand extends Command {
    /**
     * Коллекция фильмов.
     */
    private final MovieDeque movies;

    /**
     * Конструктор создает команду обновления.
     * <p>
     * Устанавливает название команды как {@code "update"} и не требует подтверждения выполнения.
     * </p>
     *
     * @param movies коллекция фильмов.
     */
    public UpdateCommand(MovieDeque movies) {
        super("update");
        this.movies = movies;
    }

    /**
     * Запрашивает у пользователя новые значения полей фильма и обновляет его.
     * <br> Если фильм с указанным ID не найден, выводится соответствующее сообщение.
     *
     * @param argument ID фильма, который требуется обновить
     * @return Результат выполнения команды.
     * @throws InvalidCommandArgumentException если аргумент не является числом
     */
    @Override
    public String execute(String argument) {
        try {
            long id = Long.parseLong(argument);
            for (Movie movie : movies.getMovies()) {
                if (movie.getId() == id) {
                    MovieData data = MovieFieldInput.inputMovieData();

                    movie.setTitle(data.title());
                    movie.setCoordinates(new Coordinates(data.x(), data.y()));
                    movie.setGenre(data.genre());
                    movie.setMpaaRating(data.rating());
                    movie.setOscarsCount(data.oscarCount());
                    movie.setDirector(data.director());

                    return "Фильм успешно обновлён";
                }
            }
            return "ID не найден";
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException("неверный формат id");
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - обновить значения элемента по ID";
    }
}
