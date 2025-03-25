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
     * @throws InvalidCommandArgumentException если аргумент не является числом
     */
    @Override
    public void execute(String argument) {
        try {
            long id = Long.parseLong(argument.trim());
            for (Movie movie : movies.getMovies()) {
                if (movie.getId() == id) {
                    String title = MovieFieldInput.inputTitle();
                    int x = MovieFieldInput.inputX();
                    Double y = MovieFieldInput.inputY();
                    MovieGenre genre = MovieFieldInput.inputGenre();
                    MpaaRating rating = MovieFieldInput.inputRating();
                    int oscarCount = MovieFieldInput.inputOscarCount();
                    Person director = MovieFieldInput.inputDirector();

                    movie.setTitle(title);
                    movie.setCoordinates(new Coordinates(x, y));
                    movie.setGenre(genre);
                    movie.setMpaaRating(rating);
                    movie.setOscarsCount(oscarCount);
                    if (director != null) {
                        movie.setDirector(director);
                        System.out.println("Фильм успешно обновлён");
                    }
                    return;
                }
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException("Неверный формат ID.");
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
