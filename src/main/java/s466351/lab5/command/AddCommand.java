package s466351.lab5.command;

import s466351.lab5.MovieFieldInput;
import s466351.lab5.movie.*;

/**
 * Добавление нового элемента в коллекцию.
 */
public class AddCommand extends Command {
    /**
     * Класс для работы с коллекцией.
     */
    private final MovieDeque movies;
    private String title;
    private int x;
    private Double y;
    private MovieGenre genre;
    private MpaaRating rating;
    private int oscarCount;
    private Person director;
    /**
     * Конструктор.
     *
     * @param movies класс для работы с коллекцией.
     */
    public AddCommand(MovieDeque movies) {
        super("add");
        this.movies = movies;
    }

    /**
     * Запрашивает ввод у пользователя, валидирует поля и добавляет элемент в коллекцию.
     *
     * @param argument Аргумент команды не влияет на выполнение.
     */
    @Override
    public void execute(String argument) {
        MovieData data = MovieFieldInput.inputMovieData();
        movies.add(data);
        System.out.println("Фильм успешно добавлен в коллекцию.");
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - добавить новый элемент в коллекцию";
    }
}
