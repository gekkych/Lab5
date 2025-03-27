package s466351.lab5.command;


import s466351.lab5.movie.MovieDeque;

/**
 * Полностью очищает коллекцию фильмов и множество ID.
 * <br> Требует подтверждения.
 */
public class ClearCommand extends Command implements Confirmable {
    /**
     * Класс для рапоты с коллекцией.
     */
    private final MovieDeque movies;

    /**
     * Конструктор.
     *
     * @param movies класс для работы с коллекцией фильмов.
     */
    public ClearCommand(MovieDeque movies) {
        super("clear");
        this.movies = movies;
    }

    /**
     * Удаляет все элементы коллекции, очищает список занятых ID и сбрасывает счётчик ID.
     *
     * @param argument Аргумент команды.
     *
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String argument) {
        movies.clear();
        return "Коллекция очищена";
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return getName() + " - удаляет все элементы коллекции";
    }

}
