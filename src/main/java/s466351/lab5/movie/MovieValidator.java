package s466351.lab5.movie;

import s466351.lab5.exception.MovieFieldNotValidatedException;

/**
 * Класс предназначен для валидации полей, связанных с фильмами, классов {@link Movie}, {@link Coordinates}, {@link Person}.
 * Методы проверяют значение на корректность и выбрасывают исключение, если значение не соответствует условиям
 */

public class MovieValidator {
    /**
     * Валидация поля title класса {@link Movie}
     * <br> title должно удовлетворять условиям:
     * <br> Не должно быть {@code null}
     * <br> Не должно быть пустой строкой
     * @param title проверяемое значение
     * @return {@code true}, если значение валидно
     * @throws MovieFieldNotValidatedException если значение title равно {@code null} или ""
     */
    public static boolean validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new MovieFieldNotValidatedException("title не должно быть null или пустой строкой");
        }
        return true;
    }

    /**
     * Валидация поля Y класса {@link Coordinates}
     * <br> Y должно удовлетворять условиям:
     * <br> Не должно быть {@code null}
     * <br> Не должно быть больше 102
     * @param y проверяемое значение
     * @return {@code true}, если значение валидно
     * @throws MovieFieldNotValidatedException если значение Y равно {@code null} или больше 102
     */
    public static boolean validateY(Double y) {
        if(y == null || y > 102) {
            throw new MovieFieldNotValidatedException("y = " + (y != null ? y : "null") + " не должно быть null или превосходить 102");
        }
        return true;
    }

    /**
     * Валидация поля oscarCount класса {@link Movie}
     * <br> oscarCount должно удовлетворять условиям:
     * <br> Не должно быть меньше нуля
     * @param oscarCount проверяемое значение
     * @return {@code true}, если значение валидно
     * @throws MovieFieldNotValidatedException если значение oscarCount меньше нуля
     */
    public static boolean validateOscarCount(int oscarCount) {
        if(oscarCount <= 0) {
            throw new MovieFieldNotValidatedException("oscarCount = " + oscarCount + " не должно быть меньше нуля");
        }
        return true;
    }

    /**
     * Валидация поля movieGenre класса {@link Movie}
     * <br> movieGenre должно удовлетворять условиям:
     * <br> Не должно быть null
     * @param genre проверяемое значение
     * @return {@code true}, если значение валидно
     * @throws MovieFieldNotValidatedException если значение genre {@code null}
     */
    public static boolean validateGenre(MovieGenre genre) {
        if (genre == null) {
            throw new MovieFieldNotValidatedException("genre не должно быть null");
        }
        return true;
    }

    /**
     * Валидация поля name класса {@link Person}
     * <br> directorName должно удовлетворять условиям:
     * <br> Не должно быть {@code null}
     * <br> Не должно быть пустой строкой
     * @param directorName проверяемое значение
     * @return {@code true}, если значение валидно
     * @throws MovieFieldNotValidatedException если значение directorName равно {@code null} или ""
     */
    public static boolean validateDirectorName(String directorName) {
        if (directorName == null || directorName.isEmpty()) {
            throw new MovieFieldNotValidatedException("directorName не должно быть null или пустой строкой");
        }
        return true;
    }

    /**
     * Валидация поля height класса {@link Person}
     * <br> height должно удовлетворять условиям:
     * <br> Не должно быть меньше нуля
     * @param height проверяемое значение
     * @return {@code true}, если значение валидно
     * @throws MovieFieldNotValidatedException Если значение height меньше нуля
     */
    public static boolean validateDirectorHeight(int height) {
        if(height <= 0) {
            throw new MovieFieldNotValidatedException("height = " + height + " не должно быть меньше нуля");
        }
        return true;
    }

    /**
     * Валидация поля weight класса {@link Person}
     * <br> weight должно удовлетворять условиям:
     * <br> Не должно быть меньше нуля
     * @param weight проверяемое значение
     * @return {@code true}, если значение валидно
     * @throws MovieFieldNotValidatedException если значение weight меньше нуля
     */
    public static boolean validateDirectorWeight(int weight) {
        if(weight <= 0) {
            throw new MovieFieldNotValidatedException("weight = " + weight + " не должно быть меньше нуля");
        }
        return true;
    }
}
