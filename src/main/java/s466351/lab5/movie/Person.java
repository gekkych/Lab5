package s466351.lab5.movie;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;
import s466351.lab5.exception.MovieFieldNotValidatedException;

/**
 * Класс Person представляет человека с параметрами имени, даты рождения, роста и веса.
 * Используется для хранения информации о человеке.
 */
@XmlRootElement
@XmlType(propOrder = {"name", "birthday", "height", "weight"})
public class Person {
    /**
     * Валидатор для проверки значений полей.
     */
    private final MovieValidator validator = new MovieValidator();
    /**
     * Имя человека. Поле не может быть null и не может быть пустым.
     */
    private String name;
    /**
     * Дата рождения человека.
     */
    private java.time.LocalDate birthday;
    /**
     * Рост человека. Должен быть больше 0.
     */
    private int height;
    /**
     * Вес человека. Должен быть больше 0.
     */
    private int weight;

    /**
     * Приватный конструктор для сериализации/десериализации объектов библиотекой JAXB
     */
    public Person() {
    }

    /**
     * Конструктор с параметрами.
     *
     * @param name Имя (не может быть null или пустым)
     * @param birthday Дата рождения (может быть null)
     * @param height Рост (должен быть больше 0)
     * @param weight Вес (должен быть больше 0)
     */
    public Person(String name, LocalDate birthday, int height, int weight) {
        setName(name);
        setBirthday(birthday);
        setWeight(weight);
        setHeight(height);
    }

    /**
     * Конструктор без даты рождения.
     *
     * @param name Имя (не может быть null или пустым)
     * @param height Рост (должен быть больше 0)
     * @param weight Вес (должен быть больше 0)
     */
    public Person(String name, int height, int weight) {
        setName(name);
        setWeight(weight);
        setHeight(height);
    }

    /**
     * Возвращает имя человека.
     *
     * @return Имя человека
     */
    @XmlElement
    public String getName() {
        return name;
    }

    /**
     * Возвращает дату рождения человека.
     *
     * @return Дата рождения или null
     */
    @XmlElement(nillable = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Возвращает рост человека.
     *
     * @return Рост (больше 0)
     */
    @XmlElement
    public int getHeight() {
        return height;
    }

    /**
     * Возвращает вес человека.
     *
     * @return Вес (больше 0)
     */
    @XmlElement
    public int getWeight() {
        return weight;
    }

    /**
     * Устанавливает имя человека.
     *
     * @param name Новое имя (не может быть null или пустым)
     * @throws MovieFieldNotValidatedException если имя некорректное
     */
    public void setName(String name) {
        if (validator.validateDirectorName(name)) {
            this.name = name;
        }
    }

    /**
     * Устанавливает дату рождения.
     *
     * @param birthday Новая дата рождения (может быть null)
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Устанавливает вес человека.
     *
     * @param weight Новый вес (должен быть больше 0)
     * @throws MovieFieldNotValidatedException если вес некорректный
     */
    public void setWeight(int weight) {
        if (validator.validateDirectorWeight(weight)) {
            this.weight = weight;
        }
    }

    /**
     * Устанавливает рост человека.
     *
     * @param height Новый рост (должен быть больше 0)
     * @throws MovieFieldNotValidatedException если рост некорректный
     */
    public void setHeight(int height) {
        if (validator.validateDirectorHeight(height)) {
            this.height = height;
        }
    }

    /**
     * Проверяет равенство объектов Person.
     * <br>Два объекта считаются равными, если:
     * <ul>
     *     <li>Ссылаются на один и тот же объект</li>
     *     <li>Принадлежат одному классу</li>
     *     <li>Имеют одинаковые значения полей: name, height, weight</li>
     * </ul>
     *
     * @param o Объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return name.equals(person.name) &&
                height == person.height &&
                weight == person.weight;
    }

    /**
     * Возвращает хэш-код объекта.
     *
     * @return Хэш-код, основанный на имени, росте и весе
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, height, weight);
    }
}
