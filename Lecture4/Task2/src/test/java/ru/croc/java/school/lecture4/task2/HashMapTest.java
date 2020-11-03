package ru.croc.java.school.lecture4.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.lecture4.task2.Exceptions.PutMapKeyFailException;
import ru.croc.java.school.lecture4.task2.Exceptions.PutMapValueFailException;
import ru.croc.java.school.lecture4.task2.books.Book;
import ru.croc.java.school.lecture4.task2.books.MarkedBook;

public class HashMapTest {

    /**
     * Проверка исключение не было брошено.
     */
    @Test
    @DisplayName("Проверка исключение не было брошено")
    public void testPutNotMarkedTypes() {
        ExtendedHashMap<Integer, Book> hashMap = new ExtendedHashMap<>();

        Integer integer = 1;
        Book book = new Book("title", "author", "genre");

        hashMap.put(integer, book);

        Assertions.assertEquals(book, hashMap.get(integer));
    }

    /**
     * Проверка что исключение было брошено придобаллении ключа.
     */
    @Test
    @DisplayName("Проверка что исключение было брошено придобаллении ключа")
    public void testPutMarkedKey() {
        ExtendedHashMap<Book, Integer> hashMap = new ExtendedHashMap<>();

        Book book = new MarkedBook("title", "author", "genre");
        Integer integer = 1;

        Assertions.assertThrows(PutMapKeyFailException.class, () -> hashMap.put(book, integer));
    }

    /**
     * Проверка что исключение было брошено придобаллении значения.
     */
    @Test
    @DisplayName("Проверка что исключение было брошено придобаллении значения")
    public void testPutMarkedValue() {
        ExtendedHashMap<Integer, Book> hashMap = new ExtendedHashMap<>();

        Integer integer = 1;
        Book book = new MarkedBook("name", "author", "genre");

        Assertions.assertThrows(PutMapValueFailException.class, () -> hashMap.put(integer, book));
    }
}
