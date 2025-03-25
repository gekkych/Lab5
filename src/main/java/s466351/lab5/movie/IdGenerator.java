package s466351.lab5.movie;

import s466351.lab5.exception.IdException;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс для генерации и валидации уникальных ID.
 */
public class IdGenerator {
    /**
     * Коллекция {@code HashSet} ID.
     */
    private final Set<Long> idSet = new HashSet<>();
    /**
     * Значение минимального доступного ID.
     */
    private long nextFreeID;

    public IdGenerator() {
        nextFreeID = 1;
    }

    /**
     * Генерирует уникальный идентификатор для нового фильма.
     *
     * @return уникальный идентификатор фильма
     */
    public long generateID() {
        while (idSet.contains(nextFreeID)) {
            nextFreeID++;
        }
        idSet.add(nextFreeID);
        return nextFreeID;
    }

    /**
     * Сбрасывает счетчик идентификаторов и очищает список занятых ID.
     * <br> Должен быть использован только при очистке коллекции.
     */
    public void resetId() {
        nextFreeID = 1;
        idSet.clear();
    }

    /**
     * Удаляет идентификатор из IdSet
     *
     * @param id идентификатор, который нужно освободить
     */
    public void releaseId(long id) {
        if(!idSet.remove(id)) {
            throw new IdException();
        }
        nextFreeID = Math.min(nextFreeID, id);
    }

    /**
     * Проверка ID в коллекции на уникальность.
     */
    public void validateId(ArrayDeque<Movie> movies) {
        int counter = 0;
        for (Movie movie : movies) {
            idSet.add(movie.getId());
            counter++;

            if (idSet.size() != counter) {
                throw new IdException();
            }
        }
    }
}
