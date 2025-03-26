package s466351.lab5;

import s466351.lab5.command.Closable;
import s466351.lab5.command.Command;
import s466351.lab5.command.Confirmable;
import s466351.lab5.command.MovieDataReceiver;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputHandler {
    /**
     * Сканер для ввода с консоли
     */
    private final Scanner scanner;
    /**
     * Карта команд, где ключ — это название команды, а значение — сам объект команды
     */
    private final HashMap<String, Command> commandMap;

    /**
     * Эмодзи, используемое в консольных выводах
     */
    private static final String GECKO = "\uD83E\uDD8E";

    public InputHandler(HashMap<String, Command> commandMap) {
        scanner = new Scanner(System.in);
        this.commandMap = commandMap;
    }

    /**
     * Запускает цикл ввода команд, где пользователь вводит команды и получает ответы от программы.
     * Команды обрабатываются, если они есть в карте команд.
     */
    public void startAppInput() {
        System.out.println("Введите help для списка команд");

        while (true) {
            try {
                System.out.print(GECKO + " > ");
                String[] input = scanner.nextLine().trim().split(" ", 2);
                String commandName = input[0];
                String argument = input.length > 1 ? input[1] : null;

                if (!commandMap.containsKey(commandName)) {
                    System.out.println("\u001B[31m" + "Команда неопознана" + "\u001B[0m");
                    continue;
                }

                Command command = commandMap.get(commandName);

                if (command instanceof Confirmable && !confirmCommand(command.getName())) {
                    continue;
                }

                try {
                    if (command instanceof MovieDataReceiver receiver) {
                        System.out.println(receiver.execute(argument, MovieFieldInput.inputMovieData()));
                    } else {
                        System.out.println(command.execute(argument));
                    }
                    if (command instanceof Closable) {
                        scanner.close();
                        return;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (NoSuchElementException e) {
                System.out.println("Приложение закрыто");
                scanner.close();
                return;
            }
        }
    }

    /**
     * Запрашивает у пользователя подтверждение на выполнение команды.
     *
     * @param commandName имя команды, для которой нужно получить подтверждение
     * @return true, если пользователь подтвердил выполнение команды, иначе false
     */
    private boolean confirmCommand(String commandName) {
        try {
            System.out.println("Вы уверены, что хотите выполнить команду " + commandName + "? (y/n)");
            System.out.print(GECKO + " > ");
            String input = scanner.nextLine().trim().toLowerCase();
            return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
