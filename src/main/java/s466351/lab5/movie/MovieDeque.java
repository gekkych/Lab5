package s466351.lab5.movie;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.*;

/**
 * Класс для работы с объектами {@link Movie}.
 * <br> Включает методы автоматической генерации ID, добавления и удаления элементов, сортировки коллекции.
 */
@XmlRootElement
public class MovieDeque {
    /**
     * Класс для работы с ID
     */
    private final IdGenerator idGenerator;
    /**
     * Коллекция {@code ArrayDeque} фильмов.
     */
    private ArrayDeque<Movie> movies = new ArrayDeque<>();
    /**
     * Дата создания коллекции.
     */
    private LocalDate creationDate;
    /**
     * Пустой конструктор для создания объекта.
     */
    public MovieDeque() {
        idGenerator = new IdGenerator();
        creationDate = LocalDate.now();
    }

    /**
     * Добавляет новый фильм в коллекцию.
     *
     * @param data содержит информацию о фильме
     */

    public void add(MovieData data) {
        Movie.MovieBuilder movieBuilder = new Movie.MovieBuilder(idGenerator.generateID(), data.title(), data.x(), data.y(), data.genre(), data.oscarCount());
        if (data.rating() != null) {
            movieBuilder.setMpaaRating(data.rating());
        }
        if (data.director() != null) {
            if (data.director().getBirthday() != null) {
                movieBuilder.setDirector(data.director().getName(), data.director().getBirthday(), data.director().getHeight(), data.director().getWeight());
            } else {
                movieBuilder.setDirector(data.director().getName(), data.director().getHeight(), data.director().getWeight());
            }
        }
        movies.add(movieBuilder.build());
        sortMovieDeque();
    }

    /**
     * Проверка ID в коллекции на уникальность после загрузки коллекции.
     */
    public void manageDeque() {
        idGenerator.validateId(movies);
    }

    /**
     * Удаляет фильм по его идентификатору.
     *
     * @param id идентификатор фильма, который нужно удалить
     */
    public void removeById(long id) {
        Iterator<Movie> iterator = movies.iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (movie.getId() == id) {
                iterator.remove();
                idGenerator.releaseId(id);
                System.out.println("Фильм успешно удалён из коллекции");
                return;
            }
        }
        System.out.println("Фильм с ID " + id + " не найден");
    }

    /**
     * Метод для удаления всех элементов коллекции и сброса множества ID
     */
    public void clear() {
        movies.clear();
        idGenerator.resetId();
    }
    /**
     * Сортирует коллекцию фильмов по идентификатору.
     */
    public void sortMovieDeque() {
        ArrayList<Movie> movieList = new ArrayList<>(movies);
        movieList.sort(Comparator.comparingLong(Movie::getId));
        movies.clear();
        movies.addAll(movieList);
    }

    /**
     * Устанавливает коллекцию фильмов (приватный).
     * <br> Используется для десериаллизации.
     * @param movies коллекция фильмов.
     */
    private void setMovies(ArrayDeque<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Возвращает коллекцию фильмов.
     *
     * @return коллекция фильмов в виде {@link ArrayDeque}
     */
    @XmlElementWrapper(name = "movies")
    @XmlElement(name = "movie")
    public ArrayDeque<Movie> getMovies() {
        return movies;
    }

    /**
     * Устанавливает дату создания (приватный).
     * <br> Используется для десериаллизации.
     * @param creationDate дата создания коллекции.
     */
    private void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    /**
     * Возвращает дату создания.
     *
     * @return Дата создания
     */
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает строковое представление коллекции.
     *
     * @return строка с информацией о коллекции
     */
    @Override
    public String toString() {
        return "Тип ArrayDeque" + "\n" +
                "Дата создания " + creationDate + "\n" +
                "Количество элементов " + getMovies().size() + "\n";
    }
}
