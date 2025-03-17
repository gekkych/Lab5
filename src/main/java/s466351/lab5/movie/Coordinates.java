package s466351.lab5.movie;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс Coordinates представляет координаты.
 * Используется для хранения данных о координатах объектов.
 */
@XmlRootElement
@XmlType(propOrder = {"x", "y"})
public class Coordinates {

    /**
     * Валидатор для проверки значений полей.
     */
    private final MovieValidator validator = new MovieValidator();
    /**
     * Координата X.
     */
    private int x;
    /**
     * Координата Y. Максимальное значение: 102. Поле не может быть null.
     */
    private Double y;

    /**
     * Приватный конструктор для сериализации/десериализации объектов библиотекой JAXB
     */
    private Coordinates() {
    }

    /**
     * Конструктор с параметрами.
     *
     * @param x Координата X
     * @param y Координата Y (не может быть null, максимальное значение 102)
     */
    public Coordinates(int x, Double y) {
        setX(x);
        setY(y);
    }

    /**
     * Устанавливает координату X.
     *
     * @param x Новое значение координаты X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Возвращает координату X.
     *
     * @return Значение координаты X
     */
    @XmlElement
    public int getX() {
        return x;
    }

    /**
     * Устанавливает координату Y, если значение проходит валидацию.
     *
     * @param y Новое значение координаты Y (не может быть null, максимум 102)
     */
    public void setY(Double y) {
        if (validator.validateY(y)) {
            this.y = y;
        }
    }

    /**
     * Возвращает координату Y.
     *
     * @return Значение координаты Y
     */
    @XmlElement
    public Double getY() {
        return y;
    }
}

