package s466351.lab5.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Выводит значение метода {@code description} всех команд.
 */
public class HelpCommand extends Command {
    /**
     * Коллекция всех команд в формате Название, Наследник Command.
     */
    private final HashMap<String, Command> commandMap;

    /**
     * Конструктор.
     *
     * @param commandMap коллекция всех команд.
     */
    public HelpCommand(HashMap<String, Command> commandMap) {
        super("help", false);
        this.commandMap = commandMap;
    }

    /**
     * Создаёт массив, состоящий из вывода {@code description()} и выводит его.
     *
     * @param argument Аргумент команды, не влияет на выполнение.
     */
    @Override
    public void start(String argument) {
        List<String> commandList = new ArrayList<>();
        System.out.println("Доступные команды:");
        for (Command command : commandMap.values()) {
            commandList.add(command.description());
        }
        Collections.sort(commandList);
        for (String description : commandList) {
            System.out.println(description);
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - справка по доступным командам";
    }
}
