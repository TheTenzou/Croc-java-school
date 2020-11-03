package ru.croc.java.school.lecture4.task2;

import ru.croc.java.school.lecture4.task2.Annotations.MapKeyFail;
import ru.croc.java.school.lecture4.task2.Annotations.MapValueFail;
import ru.croc.java.school.lecture4.task2.Exceptions.PutMapKeyFailException;
import ru.croc.java.school.lecture4.task2.Exceptions.PutMapValueFailException;

public class HashMap<K, V> extends java.util.HashMap<K, V> {
    @Override
    public V put (K key, V value) throws PutMapKeyFailException, PutMapValueFailException {
        if (key.getClass().isAnnotationPresent(MapKeyFail.class)) {
            throw new PutMapKeyFailException();
        }
        if (value.getClass().isAnnotationPresent(MapValueFail.class)) {
            throw new PutMapValueFailException();
        }
        return super.put(key, value);
    }
}
