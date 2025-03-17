package s466351.lab5.movie;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Класс элементов коллекции {@link MovieDeque}.
 * <br>Сравнение объектов происходит по количеству премий Оскар {@code oscarCount}
 *
 * <p>Содержит информация о названии, жанре, рейтинге, режиссёре, количестве оскаров и дате создания</p>
 * <p>Для сериализации и десериализации используется библиотека JAXB.</p>
 */
@XmlRootElement
@XmlType(propOrder = {"id", "title", "genre", "mpaaRating", "director", "oscarsCount", "coordinates", "creationDate"})
public class Movie implements Comparable<Movie> {
    /**
     * Валидатор содержит методы для проверки полей класса
     */
    private final MovieValidator validator = new MovieValidator();
    /**
     * Уникальный идентификатор фильма в коллекции. Генерируется автоматически
     */
    private long id;
    /**
     * Название фильма. Не может быть {@code null} или пустой строкой
     */
    private String title;
    /**
     * Координаты фильма. Не может быть {@code null}
     */
    private Coordinates coordinates;
    /**
     * Дата создания объекта. Генерируется автоматически
     */
    private java.util.Date creationDate;
    /**
     * Количество полученных премий Оскар. Должно быть больше 0
     */
    private int oscarsCount;
    /**
     * Жанр фильма из перечисления {@link MovieGenre}. Не может быть {@code null}
     */
    private MovieGenre genre;
    /**
     * Рейтинг MPAA из перечисления {@link MpaaRating}
     */
    private MpaaRating mpaaRating;
    /**
     * Режиссёр фильма. Класс {@link Person}
     */
    private Person director;

    /**
     * Приватный конструктор для сериализации/десериализации объектов библиотекой JAXB
     */
    private Movie() {
    }

    /**
     * Приватный конструктор со всеми полями для создания нового объекта фильма.
     * <br><strong>Важно:</strong> Этот конструктор используется только внутри класса
     * <br>
     * <br>Создание экземпляров происходит через {@link MovieBuilder} в {@link MovieDeque}.
     *
     * @param id          уникальный идентификатор фильма
     * @param title       название фильма
     * @param x           координата X
     * @param y           координата Y
     * @param genre       жанр фильма
     * @param mpaaRating  возрастной рейтинг фильма
     * @param oscarsCount количество полученных премий Оскар
     * @param director    режиссёр фильма
     */
    private Movie(long id, String title, int x, Double y, MovieGenre genre, MpaaRating mpaaRating, int oscarsCount, Person director) {
        setId(id);
        setTitle(title);
        setCoordinates(new Coordinates(x, y));
        setCreationDate(new Date());
        setGenre(genre);
        setMpaaRating(mpaaRating);
        setOscarsCount(oscarsCount);
        setDirector(director);
    }

    /**
     * Билдер объектов {@link Movie}
     * <br> Все обязательные параметры должны быть переданы при создании объекта {@link MovieBuilder}.
     * <br> Необязательные параметры можно установить с помощью сеттеров {@link MovieBuilder#setMpaaRating(MpaaRating)}
     * и {@link MovieBuilder#setDirector(String, LocalDate, int, int)} или {@link MovieBuilder#setDirector(String, int, int)}.
     */
    protected static class MovieBuilder {
        /**
         * Уникальный идентификатор фильма в коллекции. Генерируется автоматически
         */
        private final long id;
        /**
         * Название фильма. Не может быть {@code null} или пустой строкой
         */
        private final String title;
        /**
         * Координаты фильма. Не может быть {@code null}
         */
        private Coordinates coordinates;
        /**
         * Дата создания объекта. Генерируется автоматически
         */
        private java.util.Date creationDate;
        /**
         * Количество полученных премий Оскар. Должно быть больше 0
         */
        private final int oscarsCount;
        /**
         * Жанр фильма из перечисления {@link MovieGenre}. Не может быть {@code null}
         */
        private final MovieGenre genre;
        /**
         * Рейтинг MPAA из перечисления {@link MpaaRating}
         */
        private MpaaRating mpaaRating;
        /**
         * Режиссёр фильма. Класс {@link Person}
         */
        private Person director;

        /**
         * Конструктор для создания объекта {@link MovieBuilder}.
         * Все обязательные поля должны быть переданы в конструктор.
         *
         * @param id          уникальный идентификатор фильма.
         * @param title       название фильма.
         * @param x           координата X.
         * @param y           координата Y.
         * @param genre       жанр фильма.
         * @param oscarsCount количество полученных премий Оскар.
         */
        protected MovieBuilder(long id, String title, int x, Double y, MovieGenre genre, int oscarsCount) {
            this.id = id;
            this.title = title;
            this.coordinates = new Coordinates(x, y);
            this.genre = genre;
            this.oscarsCount = oscarsCount;
        }

        /**
         * Устанавливает возрастной рейтинг фильма.
         * Это поле является необязательным, и если оно не установлено, будет использовано значение по умолчанию {@code null}.
         *
         * @param mpaaRating возрастной рейтинг фильма.
         * @return текущий объект {@link MovieBuilder}, чтобы можно было продолжить цепочку вызовов.
         */
        protected MovieBuilder setMpaaRating(MpaaRating mpaaRating) {
            this.mpaaRating = mpaaRating;
            return this;
        }

        /**
         * Устанавливает режиссёра фильма.
         * При этом создаётся новый объект {@link Person} с указанными параметрами.
         * Это поле является необязательным, и если оно не установлено, будет использовано значение по умолчанию {@code null}.
         *
         * @param name     имя режиссёра.
         * @param birthday дата рождения режиссёра.
         * @param height   рост режиссёра.
         * @param weight   вес режиссёра.
         * @return текущий объект {@link MovieBuilder}, чтобы можно было продолжить цепочку вызовов.
         */
        protected MovieBuilder setDirector(String name, LocalDate birthday, int height, int weight) {
            this.director = new Person(name, birthday, height, weight);
            return this;
        }

        /**
         * Устанавливает режиссёра фильма без указания даты рождения.
         * При этом создаётся новый объект {@link Person} с указанными параметрами.
         * Это поле является необязательным, и если оно не установлено, будет использовано значение по умолчанию {@code null}.
         *
         * @param name   имя режиссёра.
         * @param height рост режиссёра.
         * @param weight вес режиссёра.
         * @return текущий объект {@link MovieBuilder}, чтобы можно было продолжить цепочку вызовов.
         */
        protected MovieBuilder setDirector(String name, int height, int weight) {
            this.director = new Person(name, height, weight);
            return this;
        }

        /**
         * Строит и возвращает объект {@link Movie} с текущими параметрами.
         *
         * @return новый объект {@link Movie}.
         */
        protected Movie build() {
            return new Movie(this.id, this.title, this.coordinates.getX(), this.coordinates.getY(), this.genre, this.mpaaRating, this.oscarsCount, this.director);
        }
    }


    /**
     * Возвращает уникальный идентификатор фильма в коллекции.
     *
     * @return идентификатор фильма в коллекции
     */
    @XmlElement
    public long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор фильма в коллекции.
     *
     * @param id идентификатор фильма в коллекции
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Возвращает название фильма.
     *
     * @return название фильма
     */
    @XmlElement
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название фильма, если значение проходит валидацию.
     *
     * @param title название фильма (не должно быть {@code null} или пустой строкой)
     */
    public void setTitle(String title) {
        if (validator.validateTitle(title)) {
            this.title = title;
        }
    }

    /**
     * Возвращает координаты фильма.
     *
     * @return координаты фильма
     */
    @XmlElement
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Устанавливает координаты фильма.
     *
     * @param coordinates координаты фильма
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Возвращает дату создания объекта.
     *
     * @return дата создания
     */
    @XmlElement
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Устанавливает дату создания объекта.
     *
     * @param creationDate дата создания
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Возвращает количество Оскаров.
     *
     * @return количество Оскаров
     */
    @XmlElement
    public int getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Устанавливает количество Оскаров, если значение проходит валидацию.
     *
     * @param oscarsCount количество Оскаров (не должно быть меньше 0)
     */
    public void setOscarsCount(int oscarsCount) {
        if (validator.validateOscarCount(oscarsCount)) {
            this.oscarsCount = oscarsCount;
        }
    }

    /**
     * Возвращает жанр фильма.
     *
     * @return жанр фильма
     */
    @XmlElement
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * Устанавливает жанр фильма, если значение проходит валидацию.
     *
     * @param genre жанр фильма (не должно быть {@code null})
     */
    public void setGenre(MovieGenre genre) {
        if (validator.validateGenre(genre)) {
            this.genre = genre;
        }
    }

    /**
     * Возвращает возрастной рейтинг MPAA.
     *
     * @return возрастной рейтинг MPAA
     */
    @XmlElement(nillable = true)
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Устанавливает возрастной рейтинг MPAA.
     *
     * @param mpaaRating возрастной рейтинг MPAA
     */
    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    /**
     * Возвращает режиссёра фильма.
     *
     * @return режиссёр фильма
     */
    @XmlElement(nillable = true)
    public Person getDirector() {
        return director;
    }

    /**
     * Устанавливает режиссёра фильма.
     *
     * @param director режиссёр фильма
     */
    public void setDirector(Person director) {
        this.director = director;
    }

    /**
     * Проверяет, равен ли данный объект другому объекту.
     * <br>
     * <br> Два объекта {@code Movie} считаются равными, если у них совпадают:
     * <ul>
     *     <li>Название фильма</li>
     *     <li>Количество полученных Оскаров</li>
     *     <li>Жанр</li>
     *     <li>Режиссёр</li>
     * </ul>
     *
     * @param o объект для сравнения
     * @return {@code true}, если объекты равны, иначе {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return oscarsCount == movie.oscarsCount && title.equals(movie.title) && genre.equals(movie.genre) && director.equals(movie.director);
    }

    /**
     * Возвращает хэш-код объекта.
     *
     * @return Хэш-код, основанный на названии, количестве Оскаров, жанре и режиссёре.
     */
    @Override
    public int hashCode() {
        return Objects.hash(oscarsCount, title, genre, director);
    }


    /**
     * Возвращает строковое представление объекта {@code Movie}.
     * <br>
     * <br> Формат строки включает:
     * <ul>
     *     <li>Идентификатор фильма</li>
     *     <li>Название</li>
     *     <li>Жанр</li>
     *     <li>Количество Оскаров</li>
     *     <li>Возрастное ограничение (если указано)</li>
     *     <li>Режиссёра и его дату рождения (если указаны)</li>
     * </ul>
     *
     * @return строковое представление фильма
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(id).append(")").append(" ").append(title).append("   Жанр ").append(getGenre()).append("   Количество Оскаров ").append(oscarsCount);
        if (mpaaRating != null) {
            builder.append("    Возрастное ограничение ").append(mpaaRating);
        }
        if (director != null) {
            builder.append("    Режиссёр ").append(director.getName());
            if (director.getBirthday() != null) {
                builder.append(" ").append(director.getBirthday());
            }
        }
        return builder.toString();
    }

    /**
     * Сравнивает данный фильм с другим фильмом по количеству полученных Оскаров.
     *
     * @param other другой объект {@code Movie} для сравнения
     * @return отрицательное число, если текущий фильм имеет меньше Оскаров,
     * положительное число, если больше, и 0, если их количество совпадает
     */
    @Override
    public int compareTo(Movie other) {
        return Integer.compare(this.getOscarsCount(), other.getOscarsCount());
    }
}
