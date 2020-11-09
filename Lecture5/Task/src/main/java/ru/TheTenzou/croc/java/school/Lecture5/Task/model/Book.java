package ru.TheTenzou.croc.java.school.Lecture5.Task.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Книга.
 */
public class Book {

    /**
     * Индетификатор книги.
     */
    private int id;

    /**
     * Название книги.
     */
    private String name;

    /**
     * Полное имя автора.
     */
    private String athorFullName;

    /**
     * Является ли книга новой.
     */
    private boolean isNew;

    /**
     * Дата выпуска книги.
     */
    private LocalDate releaseDate;

    /**
     * Время получения кинги.
     */
    private LocalDateTime avaliabilityDateTime;


    /**
     * Конструктор.
     *
     * @param id                   индетификатор книги
     * @param name                 название книги
     * @param athorFullName        полное имя автора книги
     * @param isNew                является ли книга новой
     * @param releaseDate          дата выпуска книги
     * @param avaliabilityDateTime время получения книги
     */
    public Book(int id, String name, String athorFullName, boolean isNew,
                LocalDate releaseDate, LocalDateTime avaliabilityDateTime) {
        this.id = id;
        this.name = name;
        this.athorFullName = athorFullName;
        this.isNew = isNew;
        this.releaseDate = releaseDate;
        this.avaliabilityDateTime = avaliabilityDateTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAthorFullName() {
        return athorFullName;
    }

    public boolean isNew() {
        return isNew;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public LocalDateTime getAvaliabilityDateTime() {
        return avaliabilityDateTime;
    }
}
