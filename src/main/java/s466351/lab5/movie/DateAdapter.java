package s466351.lab5.movie;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Адаптер для преобразования объектов {@link Date} в строковый формат и обратно при работе с JAXB.
 *
 * <p>Использует формат даты "dd-MM-yyyy".</p>
 */
public class DateAdapter extends XmlAdapter<String, Date> {
    /**
     * Форматтер для преобразования даты в строку и обратно.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    /**
     * Преобразует строку в объект {@link Date}.
     *
     * @param s строковое представление даты
     * @return объект {@link Date}, соответствующий строке
     * @throws Exception если строка не соответствует ожидаемому формату
     */
    @Override
    public Date unmarshal(String s) throws Exception {
        return dateFormat.parse(s);
    }

    /**
     * Преобразует объект {@link Date} в строку.
     *
     * @param date объект {@link Date}
     * @return строковое представление даты или {@code null}, если передан {@code null}
     * @throws Exception если возникает ошибка при форматировании
     */
    @Override
    public String marshal(Date date) throws Exception {
        return dateFormat.format(date);
    }
}
