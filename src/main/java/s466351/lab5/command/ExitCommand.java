package s466351.lab5.command;

/**
 * Команда, осуществляющая завершение программы.
 * <br> Требует подтверждение.
 */
public class ExitCommand extends Command implements Confirmable, Closable {

    /**
     * Пустой конструктор.
     */
    public ExitCommand() {
        super("exit");
    }

    /**
     * Запрашивает выход из программы.
     * <br> Метод {@code isRequiresExit()} теперь возвращает true.
     *
     * @param argument аргумент команды не влияет на исполнение программы.
     *
     * @return Результат выполнения команды.
     */
    @Override
    public String execute(String argument) {
        return "Программа завершена";
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строковое описание команды.
     */
    @Override
    public String description() {
        return this.getName() + " - завершение программы (без сохранения файла)";
    }
}
