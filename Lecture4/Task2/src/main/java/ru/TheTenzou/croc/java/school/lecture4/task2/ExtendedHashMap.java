package ru.TheTenzou.croc.java.school.lecture4.task2;

import ru.TheTenzou.croc.java.school.lecture4.task2.Annotations.MapKeyFail;
import ru.TheTenzou.croc.java.school.lecture4.task2.Annotations.MapValueFail;
import ru.TheTenzou.croc.java.school.lecture4.task2.Exceptions.PutMapKeyFailException;
import ru.TheTenzou.croc.java.school.lecture4.task2.Exceptions.PutMapValueFailException;

/**
 * Расширение HashMap которое не принимает значения отмечение анотациями: {@link MapKeyFail}, {@link MapValueFail}
 *
 * @param <K> тип ключа
 * @param <V> тип значения
 */
public class ExtendedHashMap<K, V> extends java.util.HashMap<K, V> {

    /**
     * Асоциирует заначение с заданым ключом.
     *
     * @param key   ключ с которым асоциируется заначение
     * @param value заначение которое будет асоциироватся с ключом
     * @return предыдушее заначение асоциируемое с заданым ключом
     * @throws PutMapKeyFailException   если ключь отмечен анотацией {@link MapKeyFail}
     * @throws PutMapValueFailException если значение отмечено анотацией {@link MapValueFail}
     */
    @Override
    public V put(K key, V value) throws PutMapKeyFailException, PutMapValueFailException {
        if (key.getClass().isAnnotationPresent(MapKeyFail.class)) {
            throw new PutMapKeyFailException();
        }
        if (value.getClass().isAnnotationPresent(MapValueFail.class)) {
            throw new PutMapValueFailException();
        }
        return super.put(key, value);
    }
}
