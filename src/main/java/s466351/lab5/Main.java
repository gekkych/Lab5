package s466351.lab5;

import s466351.lab5.command.AddIfMaxCommand;
import s466351.lab5.command.*;
import s466351.lab5.exception.MovieDequeException;
import s466351.lab5.movie.MovieDeque;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Главный класс программы, управляющий запуском и обработкой команд.
 * Этот класс инициализирует приложение, загружает данные, управляет командами и взаимодействует с пользователем.
 * <p>
 * Программа предоставляет возможность работать с коллекцией фильмов, а также сохранять данные в файл и загружать их из файла.
 * Классы команд, такие как {@link AddCommand}, {@link ShowCommand}, {@link RemoveByIdCommand}, реализуют различные операции
 * над коллекцией фильмов.
 * <p>
 * Взаимодействие с пользователем осуществляется через консоль, где пользователь может вводить команды.
 */
public class Main {
    /**
     * Коллекция фильмов
     */
    static MovieDeque movies;

    /**
     * Управление пользовательским вводом
     */

    static InputHandler input;

    /**
     * Менеджер сохранения данных в файл
     */
    static SaveManager saveManager;

    /**
     * Путь к файлу для сохранения данных
     */
    static Path filePath;

    /**
     * Карта команд, где ключ — это название команды, а значение — сам объект команды
     */
    static HashMap<String, Command> commandMap = new HashMap<>();

    /**
     * Точка входа в программу.
     * <p>
     * Этот метод загружает данные из файла (или создает новый файл, если он не существует),
     * инициализирует команды и запускает цикл ввода команд с консоли.
     * </p>
     *
     * @param args Аргументы командной строки. Если передан путь к файлу, он будет использован для сохранения данных.
     */
    public static void main(String[] args) {
        filePath = Paths.get("save.xml");

        if (args.length > 0 && args[0].equals("/dev/zero")) {
            System.out.println("\u001B[31m" + "Ошибка: Ввод с /dev/zero не поддерживается!" + "\u001B[0m");
            return;
        }

        if (args.length > 0 && !args[0].isBlank()) {
            Path inputPath = Paths.get(args[0]).toAbsolutePath();
            if (Files.isDirectory(inputPath)) {
                System.out.println("Ошибка: указанный путь является папкой. Используется стандартный save.xml.");
            } else {
                filePath = inputPath;
            }
        }

        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
                System.out.println("Создан новый файл: " + filePath.toAbsolutePath());
            } catch (IOException e) {
                System.out.println("Ошибка при создании файла: " + e.getMessage());
                return;
            }
        }

        saveManager = new SaveManager(filePath.toString());
        input = new InputHandler(commandMap);

        try {
            movies = saveManager.loadFromXML();
            movies.manageDeque();
            initializeCommands();
            input.startAppInput();
        } catch (MovieDequeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Инициализирует все доступные команды и добавляет их в карту команд.
     * Каждая команда имеет свое действие и может быть выполнена по запросу пользователя.
     */
    public static void initializeCommands() {
        ExitCommand exit = new ExitCommand();
        HelpCommand help = new HelpCommand(commandMap);
        ShowCommand show = new ShowCommand(movies);
        RemoveByIdCommand remove_by_id = new RemoveByIdCommand(movies);
        ClearCommand clear = new ClearCommand(movies);
        AddCommand add = new AddCommand(movies);
        SumOfOscarCountCommand sumOfOscar = new SumOfOscarCountCommand(movies);
        AverageOfOscarCountCommand averageOfOscar = new AverageOfOscarCountCommand(movies);
        InfoCommand info = new InfoCommand(movies);
        UpdateCommand update = new UpdateCommand(movies);
        SaveCommand save = new SaveCommand(movies, saveManager);
        AddIfMaxCommand addIfMax = new AddIfMaxCommand(movies);
        AddIfMinCommand addIfMin = new AddIfMinCommand(movies);
        RemoveIfLowerCommand removeIfLower = new RemoveIfLowerCommand(movies);
        GroupByGenreCommand groupByGenre = new GroupByGenreCommand(movies);

        addCommand(help);
        addCommand(exit);
        addCommand(show);
        addCommand(remove_by_id);
        addCommand(clear);
        addCommand(add);
        addCommand(sumOfOscar);
        addCommand(averageOfOscar);
        addCommand(info);
        addCommand(update);
        addCommand(save);
        addCommand(addIfMax);
        addCommand(addIfMin);
        addCommand(removeIfLower);
        addCommand(groupByGenre);
    }

    /**
     * Добавляет команду в карту команд по ее имени.
     *
     * @param command команда, которую нужно добавить в карту
     */
    public static void addCommand(Command command) {
        commandMap.put(command.getName(), command);
    }
}

