package ru.TheTenzou.croc.java.school.Lecture6;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;

/**
 * Класс для нахождения максимального элемента массива.
 *
 * @param <T> тип элементов массива
 */
public class FindMax<T extends Comparable<T>> implements Callable<T> {

    /**
     * Сортируемы массив
     */
    private Collection<T> collection;

    /**
     * Конструктор.
     *
     * @param collection коллекция
     */
    public FindMax(Collection<T> collection) {
        this.collection = collection;
    }

    /**
     * Метод поиска максимума.
     *
     * @return максимальый элемен массива.
     */
    @Override
    public T call() {
        if (collection != null) {
            return Collections.max(collection);
        }
        return null;
    }
}
