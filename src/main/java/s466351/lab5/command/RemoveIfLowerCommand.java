package s466351.lab5.command;

import s466351.lab5.MovieFieldInput;
import s466351.lab5.movie.*;

import java.util.Iterator;

/**
 * Удаляет все элементы коллекции меньше заданного.\
 * <br> Требует подтверждения.
 */
public class RemoveIfLowerCommand extends Command{
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
        super("remove_if_lower", true);
        this.movies = movies;
    }

    /**
     * Получает ввод от пользователя и сравнивает значения полей введённого и имеющихся элементов.
     * <br> Удаляет все, что меньше.
     *
     * @param argument аргумент команды, не влияет на выполнение.
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

        Iterator<Movie> iterator = movies.getMovies().iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (oscarCount > movie.getOscarsCount()) {
                System.out.println("Удалён фильм " + movie.getTitle() + " с айди " + movie.getId());
                iterator.remove();
            }
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
