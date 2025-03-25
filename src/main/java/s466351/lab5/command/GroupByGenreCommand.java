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
        super("group_by_genre", false);
        this.movies = movies;
    }

    /**
     * Группирует фильмы в три {@code ArrayList}, после если они не пусты выводит их.
     *
     * @param argument Аргумент команды, не влияет на выполнение команды.
     */
    @Override
    public void start(String argument) {
        ArrayList<String> actionFilms = new ArrayList<>();
        ArrayList<String> comedyFilms = new ArrayList<>();
        ArrayList<String> scifiFilms = new ArrayList<>();
        for (Movie movie : movies.getMovies()) {
            if (movie.getGenre() == null) continue;
            switch (movie.getGenre()) {
                case ACTION -> actionFilms.add(movie.toString());
                case COMEDY -> comedyFilms.add(movie.toString());
                case SCIENCE_FICTION -> scifiFilms.add(movie.toString());
            }
        }
        if (!actionFilms.isEmpty()) {
            System.out.println("Боевики:");
            for (String s : actionFilms) {
                System.out.println(s);
            }
        }
        if (!comedyFilms.isEmpty()) {
            System.out.println("Комедии: ");
            for (String s : comedyFilms) {
                System.out.println(s);
            }
        }
        if (!scifiFilms.isEmpty()) {
            System.out.println("Научная фантастика: ");
            for (String s : scifiFilms) {
                System.out.println(s);
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
        return getName() + " - сгруппировать фильма по ID";
    }
}
