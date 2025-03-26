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
        super("help");
        this.commandMap = commandMap;
    }

    /**
     * Создаёт массив, состоящий из вывода {@code description()}.
     *
     * @param argument Аргумент команды, не влияет на выполнение.
     *
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String argument) {
        StringBuilder result = new StringBuilder();
        List<String> commandList = new ArrayList<>();
        result.append("Доступные команды: ").append("\n");
        for (Command command : commandMap.values()) {
            commandList.add(command.description());
        }
        Collections.sort(commandList);
        for (String description : commandList) {
            result.append(description).append("\n");
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
        return this.getName() + " - справка по доступным командам";
    }
}
