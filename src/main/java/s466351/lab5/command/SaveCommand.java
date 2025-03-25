package s466351.lab5.command;

import s466351.lab5.SaveManager;
import s466351.lab5.movie.MovieDeque;

/**
 * Команда для сохранения коллекции фильмов в XML-файл.
 * <br> Требует подтверждения.
 * <p>
 * Использует {@link SaveManager} для выполнения операции сохранения.
 * </p>
 */
public class SaveCommand extends Command implements Confirmable {
    /**
     * Коллекция фильмов.
     */
    private final MovieDeque movies;

    /**
     * Менеджер сохранения, отвечающий за сериализацию коллекции.
     */
    private final SaveManager saveManager;

    /**
     * Конструктор.
     *
     * @param movies      коллекция фильмов, подлежащая сохранению
     * @param saveManager объект {@link SaveManager} для сохранения коллекции
     */
    public SaveCommand(MovieDeque movies, SaveManager saveManager) {
        super("save");
        this.movies = movies;
        this.saveManager = saveManager;
    }

    /**
     * Выполняет команду сохранения.
     * <p>
     * Сохраняет коллекцию в XML-файл и выводит сообщение об успешном сохранении.
     * </p>
     *
     * @param argument аргумент команды, не влияет на выполнение.
     */
    @Override
    public void execute(String argument) {
        saveManager.saveInXML(movies);
        System.out.println("Коллекция сохранена в файл " + saveManager.getFileName());
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - сохранить коллекцию в файл";
    }
}

