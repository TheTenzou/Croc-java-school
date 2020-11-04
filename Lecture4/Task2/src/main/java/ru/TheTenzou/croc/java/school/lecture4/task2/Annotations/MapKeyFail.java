package ru.TheTenzou.croc.java.school.lecture4.task2.Annotations;

import ru.TheTenzou.croc.java.school.lecture4.task2.ExtendedHashMap;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Отмечаются типы которые не разрешы для использования в ExtendedHashMap как ключ.
 *
 * @see ExtendedHashMap
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MapKeyFail {
}
