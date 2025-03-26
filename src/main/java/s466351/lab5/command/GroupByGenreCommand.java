package s466351.lab5.command;


import s466351.lab5.movie.Movie;
import s466351.lab5.movie.MovieDeque;

import java.util.ArrayList;

/**
 * Группирует и выводит фильмы по значение {@code genre}.
 */
public class GroupByGenreCommand extends Command{
    /**
     * Класс для работы с коллекцией.
     */
    MovieDeque movies;

    /**
     * Конструктор
     *
     * @param movies класс для работы с коллекцией.
     */
    public GroupByGenreCommand(MovieDeque movies) {
        super("group_by_genre");
        this.movies = movies;
    }

    /**
     * Группирует фильмы в три {@code ArrayList}, после если они не пусты выводит их.
     *
     * @param argument Аргумент команды, не влияет на выполнение команды.
     *
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String argument) {
        ArrayList<String> actionFilms = new ArrayList<>();
        ArrayList<String> comedyFilms = new ArrayList<>();
        ArrayList<String> scifiFilms = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        for (Movie movie : movies.getMovies()) {
            if (movie.getGenre() == null) continue;
            switch (movie.getGenre()) {
                case ACTION -> actionFilms.add(movie.toString());
                case COMEDY -> comedyFilms.add(movie.toString());
                case SCIENCE_FICTION -> scifiFilms.add(movie.toString());
            }
        }
        if (!actionFilms.isEmpty()) {
            result.append("Боевики: ").append("\n");
            for (String s : actionFilms) {
                result.append(s).append("\n");
            }
        }
        if (!comedyFilms.isEmpty()) {
            result.append("Комедии: ").append("\n");
            for (String s : comedyFilms) {
                result.append(s).append("\n");
            }
        }
        if (!scifiFilms.isEmpty()) {
            result.append("Научная фантастика: ").append("\n");
            for (String s : scifiFilms) {
                result.append(s).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return getName() + " - сгруппировать фильма по ID";
    }
}
