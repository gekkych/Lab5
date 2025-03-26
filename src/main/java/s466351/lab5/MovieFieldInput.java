package s466351.lab5;

import s466351.lab5.exception.MovieFieldNotValidatedException;
import s466351.lab5.movie.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static s466351.lab5.movie.MovieValidator.*;

/**
 * Класс для ввода данных о фильме с валидацией.
 * <br> Содержит статические методы для ввода названия, координат, жанра, рейтинга и данных о режиссёре.
 */
public class MovieFieldInput {
    private static final Scanner scanner = new Scanner(System.in);
    static final String GECKO = "\uD83E\uDD8E";

    /**
     * Запрашивает у пользователя название фильма и проверяет его валидность.
     *
     * @return Валидированное название фильма.
     */
    public static String inputTitle() {
        while (true) {
            System.out.println("Введите название фильма (строка, не должно быть null или пустой строкой):");
            System.out.print(GECKO + " > ");
            String title = scanner.nextLine().trim();
            try {
                if (validateTitle(title)) {
                    return title;
                }
            } catch (MovieFieldNotValidatedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя координату X.
     *
     * @return Числовое значение координаты X.
     */
    public static int inputX() {
        while (true) {
            System.out.println("Введите координату Х (число):");
            System.out.print(GECKO + " > ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Поле x должно быть числом.");
            }
        }
    }

    /**
     * Запрашивает у пользователя координату Y и проверяет её валидность.
     *
     * @return Валидированное значение координаты Y.
     */
    public static Double inputY() {
        while (true) {
            System.out.println("Введите координату Y (число, должно быть меньше 102):");
            System.out.print(GECKO + " > ");
            try {
                Double y = Double.parseDouble(scanner.nextLine().trim());
                try {
                    if (validateY(y)) {
                        return y;
                    }
                } catch (MovieFieldNotValidatedException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("Значение y должно быть числом.");
            }
        }
    }

    /**
     * Запрашивает у пользователя количество Оскаров и проверяет его валидность.
     *
     * @return Валидированное количество Оскаров.
     */
    public static int inputOscarCount() {
        while (true) {
            System.out.println("Введите количество оскаров (число, больше 0):");
            System.out.print(GECKO + " > ");
            try {
                int oscarCount = Integer.parseInt(scanner.nextLine().trim());
                try {
                    if (validateOscarCount(oscarCount)) return oscarCount;
                } catch (MovieFieldNotValidatedException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("Значение oscarCount должно быть числом.");
            }
        }
    }

    /**
     * Запрашивает у пользователя жанр фильма.
     *
     * @return Объект {@link MovieGenre}, соответствующий введённому значению.
     */
    public static MovieGenre inputGenre() {
        while (true) {
            System.out.println("Введите жанр фильма:");
            System.out.println("Action, Comedy, Science fiction");
            System.out.print(GECKO + " > ");
            MovieGenre genre = switch (scanner.nextLine().trim().toLowerCase()) {
                case "action" -> MovieGenre.ACTION;
                case "comedy" -> MovieGenre.COMEDY;
                case "science fiction" -> MovieGenre.SCIENCE_FICTION;
                default -> null;
            };
            try {
                if (validateGenre(genre)) return genre;
            } catch (MovieFieldNotValidatedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя возрастной рейтинг фильма.
     *
     * @return Объект {@link MpaaRating}, соответствующий введённому значению, или {@code null}, если поле оставлено пустым.
     */
    public static MpaaRating inputRating() {
        while (true) {
            System.out.println("Введите возрастной рейтинг фильма (нажмите Enter если хотите оставить поле пустым):");
            System.out.println("G, PG, PG 13, NC 17");
            System.out.print(GECKO + " > ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                return null;
            }
            MpaaRating mpaaRating = switch (input) {
                case "g" -> MpaaRating.G;
                case "pg" -> MpaaRating.PG;
                case "pg 13" -> MpaaRating.PG_13;
                case "nc 17" -> MpaaRating.NC_17;
                default -> null;
            };
            if (mpaaRating == null) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
                continue;
            }
            return mpaaRating;
        }
    }

    /**
     * Запрашивает у пользователя данные о режиссёре (опционально).
     *
     * @return Объект {@link Person}, содержащий информацию о режиссёре, или {@code null}, если пользователь отказался вводить данные.
     */
    public static Person inputDirector() {
        final String[] CONFIRMATION_WORDS = {"y", "yes"};
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String directorName;
        LocalDate birthday = null;
        int height;
        int weight;
        boolean confirmed = false;
        System.out.println("Вы хотите ввести информацию о режиссёре? [y/n]:");
        System.out.print(GECKO + " > ");
        String input = scanner.nextLine().trim();
        for (String word : CONFIRMATION_WORDS) {
            if (input.equalsIgnoreCase(word)) {
                confirmed = true;
                break;
            }
        }
        if (!confirmed) {
            return null;
        }

        while (true) {
            System.out.println("Введите имя режиссёра (строка, не должно быть null или пустой строкой):");
            System.out.print(GECKO + " > ");
            directorName = scanner.nextLine().trim();
            try {
                if (validateDirectorName(directorName)) {
                    break;
                }
            } catch (MovieFieldNotValidatedException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите дату рождения режиссёра в формате \"dd-MM-yyyy:\" (нажмите Enter если хотите оставить поле пустым):");
            System.out.print(GECKO + " > ");
            String birthdayInput = scanner.nextLine().trim();
            if (birthdayInput.isEmpty()) {
                break;
            }
            try {
                birthday = LocalDate.parse(birthdayInput, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
            }
        }

        while (true) {
            System.out.println("Введите рост режиссёра (число, больше 0):");
            System.out.print(GECKO + " > ");
            try {
                height = Integer.parseInt(scanner.nextLine().trim());
                try {
                    if (validateDirectorHeight(height)) break;
                } catch (MovieFieldNotValidatedException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("height должно быть числом.");
            }
        }

        while (true) {
            System.out.println("Введите вес режиссёра:");
            System.out.print(GECKO + " > ");
            try {
                weight = Integer.parseInt(scanner.nextLine().trim());
                try {
                    if (validateDirectorWeight(weight)) break;
                } catch (MovieFieldNotValidatedException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("weight должно быть числом");
            }
        }
        if (birthday != null) {
            return new Person(directorName, birthday, height, weight);
        } else {
            return new Person(directorName, height, weight);
        }
    }

    /**
     * Запрашивает у пользователя данные о фильме и возвращает их в виде объекта {@link MovieData}.
     * Использует методы класса для ввода каждого поля.
     *
     * @return объект MovieData с введёнными пользователем данными
     */
    public static MovieData inputMovieData() {

        return new MovieData(
                inputTitle(),
                inputX(),
                inputY(),
                inputGenre(),
                inputRating(),
                inputOscarCount(),
                inputDirector()
        );

    }
}
