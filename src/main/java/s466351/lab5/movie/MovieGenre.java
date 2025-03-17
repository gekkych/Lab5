package s466351.lab5.movie;

/**
 * Перечисление жанров фильмов.
 * <p> </p>
 * <ul>
 *     <li>{@link #ACTION} - боевик</li>
 *     <li>{@link #COMEDY} - комедия</li>
 *     <li>{@link #SCIENCE_FICTION} - научная фантастика</li>
 * </ul>
 */
public enum MovieGenre {
    /**
     * Action.
     * Боевик.
     */
    ACTION("Action"),
    /**
     * Comedy.
     * Комедия.
     */
    COMEDY("Comedy"),
    /**
     * Science fiction.
     * Научная фантастика.
     */
    SCIENCE_FICTION("Science fiction");

    private final String value;

    /**
     * Конструктор для создания экземпляра жанра.
     *
     * @param value Текстовое представление жанра
     */
    MovieGenre(String value) {
        this.value = value;
    }

    /**
     * Текстовое представление жанра.
     *
     * @return Текстовое значение жанра
     */
    @Override
    public String toString() {
        return value;
    }
}
