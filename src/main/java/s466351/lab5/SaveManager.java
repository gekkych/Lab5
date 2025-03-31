package s466351.lab5;


import s466351.lab5.exception.CommandException;
import s466351.lab5.exception.MovieDequeException;
import s466351.lab5.movie.MovieDeque;
import s466351.lab5.movie.MovieValidator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Scanner;

/**
 * Класс для управления сохранением и загрузкой коллекции {@link MovieDeque} в XML-файл.
 * <p>
 * Использует библиотеку JAXB для сериализации и десериализации объектов.
 * </p>
 */
public class SaveManager {
    /**
     * Контекст JAXB, используемый для создания маршаллера и анмаршаллера.
     */
    JAXBContext context;

    /**
     * Объект для сериализации {@code MovieDeque} в XML.
     */
    Marshaller marshaller;

    /**
     * Объект для десериализации XML в {@code MovieDeque}.
     */
    Unmarshaller unmarshaller;

    /**
     * Файл, используемый для хранения коллекции фильмов.
     */
    File file;

    /**
     * Конструктор создает менеджер сохранения с указанным путем к файлу.
     * <p>Инициализирует JAXB-контекст, маршаллер и анмаршаллер.</p>
     *
     * @param filePath путь к файлу для сохранения данных
     */
    public SaveManager(String filePath) {
        file = new File(filePath);
        try {
            context = JAXBContext.newInstance(MovieDeque.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            System.out.println("Ошибка при сериализации/десериализации");
        }
    }

    /**
     * Получает имя файла, используемого для хранения коллекции.
     *
     * @return имя файла
     */
    public String getFileName() {
        return file.getName();
    }

    /**
     * Сохраняет коллекцию фильмов в XML-файл.
     * <p>Файл создается, если он не существует.</p>
     *
     * @param movies коллекция фильмов для сохранения
     */
    public void saveInXML(MovieDeque movies) {
        prepareSaveFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(convertToXML(movies).toString());
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении");
        }
    }

    /**
     * Загружает коллекцию фильмов из XML-файла.
     * <p>Если файл не найден или пуст, создается новая пустая коллекция.</p>
     *
     * @return загруженная коллекция фильмов или новая коллекция в случае ошибки
     */
    public MovieDeque loadFromXML() {
        if (!file.exists()) return new MovieDeque();
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder xmlContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                xmlContent.append(scanner.nextLine()).append("\n");
            }
            if (xmlContent.isEmpty()) {
                return new MovieDeque();
            }
            StringReader stringReader = new StringReader(xmlContent.toString());
            MovieDeque result = (MovieDeque) unmarshaller.unmarshal(stringReader);
            if (result == null) return new MovieDeque();

            return result;
        } catch (JAXBException | FileNotFoundException e) {
            throw new MovieDequeException("Возможно XML файл повреждён. " + file.getAbsolutePath());
        }
    }

    /**
     * Конвертирует коллекцию фильмов в XML-строку.
     *
     * @param movies коллекция фильмов для сериализации
     * @return объект {@code StringWriter}, содержащий XML-представление коллекции
     * @throws CommandException если возникает ошибка сериализации
     */
    public StringWriter convertToXML(MovieDeque movies) throws CommandException {
        try {
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(movies, stringWriter);
            return stringWriter;
        } catch (JAXBException e) {
            throw new CommandException("Ошибка при сериализации.");
        }
    }

    /**
     * Подготавливает файл для сохранения, создавая его, если он не существует.
     *
     * @throws CommandException если не удается создать файл
     */
    private void prepareSaveFile() throws CommandException {
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Создан новый файл сохранения");
                }
            }
        } catch (IOException e) {
            throw new CommandException("Ошибка при создании файла");
        }
    }
}
