package s466351.lab5.command;


import s466351.lab5.movie.MovieDeque;

/**
 * Выводит информацию о коллекции.
 */
public class InfoCommand extends Command{
    /**
     * Класс для работы с коллекцией.
     */
    MovieDeque movies;

    /**
     * Конструктор.
     *
     * @param movies класс для работы с коллекцией
     */
    public InfoCommand(MovieDeque movies) {
        super("info");
        this.movies = movies;
    }

    /**
     * Выводит строковое представление MovieDeque
     * @param arguments Аргумент команды, не влияет на выполнение.
     */
    @Override
    public void execute(String arguments) {
        System.out.printf(movies.toString());
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - информация о коллекции";
    }
}
