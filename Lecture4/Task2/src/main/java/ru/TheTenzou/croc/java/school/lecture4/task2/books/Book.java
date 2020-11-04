package ru.TheTenzou.croc.java.school.lecture4.task2.books;

/**
 * Книга.
 */
public class Book {
    /**
     * Название книги.
     */
    String name;
    /**
     * Автор книги.
     */
    String author;
    /**
     * Жанр книги.
     */
    String genre;

    /**
     * Конструктор.
     * @param name название
     * @param author автор
     * @param genre жанр
     */
    public Book(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
