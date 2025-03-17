package s466351.lab5.command;


import s466351.lab5.exception.InvalidCommandArgumentException;
import s466351.lab5.movie.MovieDeque;

/**
 * Удаление элемента коллекции по значению {@code ID}.
 * <br> Требует подтверждение.
 */
public class RemoveByIdCommand extends Command {
    /**
     * Класс для работы с коллекцией фильмов.
     */
    private final MovieDeque movies;

    /**
     * Конструктор.
     *
     * @param movies класс для работы с коллекцией.
     */
    public RemoveByIdCommand(MovieDeque movies) {
        super("remove_by_id", true);
        this.movies = movies;
    }

    /**
     * Удаление элемента коллекции с заданным ID.
     *
     * @param argument ID, по значении которого нужно удалить элемент.
     * @throws InvalidCommandArgumentException если значение аргумента не является числом.
     */
    @Override
    public void start(String argument) {
        try {
            long id = Long.parseLong(argument);
            movies.removeById(id);
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
        return this.getName() + " - удалить элемент по ID";
    }
}
