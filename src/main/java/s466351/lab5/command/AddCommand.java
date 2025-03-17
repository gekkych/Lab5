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

    /**
     * Конструктор.
     *
     * @param movies класс для работы с коллекцией.
     */
    public AddCommand(MovieDeque movies) {
        super("add", false);
        this.movies = movies;
    }

    /**
     * Запрашивает ввод у пользователя, валидирует поля и добавляет элемент в коллекцию.
     *
     * @param argument Аргумент команды не влияет на выполнение.
     */
    @Override
    public void start(String argument) {
        String title = MovieFieldInput.inputTitle();
        int x = MovieFieldInput.inputX();
        Double y = MovieFieldInput.inputY();
        MovieGenre genre = MovieFieldInput.inputGenre();
        MpaaRating rating = MovieFieldInput.inputRating();
        int oscarCount = MovieFieldInput.inputOscarCount();
        Person director = MovieFieldInput.inputDirector();

        movies.add(title, x, y, genre, rating, oscarCount, director);
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
