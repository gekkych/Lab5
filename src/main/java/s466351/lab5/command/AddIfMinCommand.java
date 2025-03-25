package s466351.lab5.command;

import s466351.lab5.MovieFieldInput;
import s466351.lab5.exception.MovieCannotBeAddedException;
import s466351.lab5.movie.*;

/**
 * Добавление нового элемента в коллекцию, если его значение {@code oscarCount} минимально в коллекции.
 */
public class AddIfMinCommand extends Command{
    /**
     * Класс для работы с коллекцией.
     */
    private final MovieDeque movies;
    /**
     * Конструктор.
     *
     * @param movies класс для работы с коллекцией.
     */
    public AddIfMinCommand(MovieDeque movies) {
        super("add_if_max", false);
        this.movies = movies;
    }

    /**
     * Запрашивает ввод у пользователя, валидирует поля и добавляет элемент в коллекцию, если его значение {@code oscarCount} минимально в коллекции.
     *
     * @param argument Аргумент команды не влияет на выполнение.
     */
    @Override
    public void start(String argument) {
        int oscarCount = MovieFieldInput.inputOscarCount();
        for(Movie movie : movies.getMovies()) {
            if (oscarCount >= movie.getOscarsCount()) {
                throw new MovieCannotBeAddedException("значение oscarCount не минимально.");
            }
        }
        String title = MovieFieldInput.inputTitle();
        int x = MovieFieldInput.inputX();
        Double y = MovieFieldInput.inputY();
        MovieGenre genre = MovieFieldInput.inputGenre();
        MpaaRating rating = MovieFieldInput.inputRating();
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
        return this.getName() + " - добавить новый элемент в коллекцию, если его значение не превышает значение наибольшего элемента этой коллекции";
    }
}
