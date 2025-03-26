package s466351.lab5.movie;
import static s466351.lab5.movie.MovieValidator.*;

/**
 * Содержит данные о фильме для передачи между классами.
 * Все поля соответствуют полям класса {@link Movie}.
 *
 * @param title      название фильма
 * @param x          координата X
 * @param y          координата Y (не больше 102)
 * @param genre      жанр фильма
 * @param rating     возрастной рейтинг (может быть null)
 * @param oscarCount количество оскаров (больше 0)
 * @param director   режиссёр (может быть null)
 */
public record MovieData(String title, int x, Double y, MovieGenre genre, MpaaRating rating, int oscarCount,
                        Person director) {
    public MovieData {
        validateTitle(title);
        validateY(y);
        validateGenre(genre);
        validateOscarCount(oscarCount);
        if (director != null) {
            validateDirectorName(director.getName());
            validateDirectorHeight(director.getHeight());
            validateDirectorWeight(director.getWeight());
        }
    }
}