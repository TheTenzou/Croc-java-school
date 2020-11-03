package ru.croc.java.school.lecture4.task2.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Отмечаются типы которые не разрешы для использования в ExtendedHashMap как значение.
 *
 * @see ru.croc.java.school.lecture4.task2.ExtendedHashMap
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MapValueFail {
}
