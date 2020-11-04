package ru.TheTenzou.croc.java.school.lecture4.task2.books;

import ru.TheTenzou.croc.java.school.lecture4.task2.ExtendedHashMap;
import ru.TheTenzou.croc.java.school.lecture4.task2.Annotations.MapKeyFail;
import ru.TheTenzou.croc.java.school.lecture4.task2.Annotations.MapValueFail;

/**
 * Отмеченая книга, которая не может быть полем или ключем в ExtendedHashMap
 *
 * @see ExtendedHashMap
 */
@MapKeyFail
@MapValueFail
public class MarkedBook extends Book {

    /**
     * Конструктор.
     * @param name название
     * @param author автор
     * @param genre жанр
     */
    public MarkedBook(String name, String author, String genre) {
        super(name, author, genre);
    }
}
