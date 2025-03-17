package s466351.lab5.movie;

/**
 * Перечисление рейтингов MPAA.
 * <br> Используется для классификации фильмов по возрастному ограничению.
 * <p> </p>
 *     <ul>
 *         <li>{@link #G} - без ограничений</li>
 *         <li>{@link #PG} - рекомендуется присутствие родителей</li>
 *         <li>{@link #PG_13} - не рекомендуется детям до 13 лет, без присутствия родителей</li>
 *         <li>{@link #NC_17} - запрещено лицам до 17 лет</li>
 *     </ul>
 */
public enum MpaaRating {
    /**
     * G - General Audience.
     * Фильм для всех возрастов.
     */
    G("G"),
    /**
     * PG - Parental Guidance Suggested.
     * Некоторые сцены могут быть неприемлемыми для детей.
     */
    PG("PG"),
    /**
     * PG 13 - Parents Strongly Cautioned.
     * Может содержать сцены насилия или ненормативную лексику.
     */
    PG_13("PG 13"),
    /**
     * NC 17 - Adults Only.
     * Лицам младше 17 лет просмотр запрещен.
     */
    NC_17("NC 17");

    private final String value;

    /**
     * Конструктор для создания экземпляра рейтинга.
     *
     * @param value Текстовое представление рейтинга
     */
    MpaaRating(String value) {
        this.value = value;
    }

    /**
     * Текстовое представление рейтинга MPAA.
     *
     * @return Текстовое значение рейтинга
     */
    @Override
    public String toString() {
        return value;
    }
}
