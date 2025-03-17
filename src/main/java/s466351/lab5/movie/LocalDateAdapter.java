package s466351.lab5.movie;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Адаптер для преобразования объектов {@link LocalDate} в строковый формат и обратно при работе с JAXB.
 *
 * <p>Использует формат даты "dd-MM-yyyy".</p>
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    /**
     * Форматтер для преобразования даты в строку и обратно.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Преобразует строку в объект {@link LocalDate}.
     *
     * @param s строковое представление даты
     * @return объект {@link LocalDate}, соответствующий строке
     * @throws Exception если строка не соответствует ожидаемому формату
     */
    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s, formatter);
    }

    /**
     * Преобразует объект {@link LocalDate} в строку.
     *
     * @param localDate объект {@link LocalDate}
     * @return строковое представление даты или {@code null}, если передан {@code null}
     * @throws Exception если возникает ошибка при форматировании
     */
    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate != null ? localDate.format(formatter) : null;
    }
}
