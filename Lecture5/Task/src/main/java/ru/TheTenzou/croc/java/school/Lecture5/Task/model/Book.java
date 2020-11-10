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
    private String title;

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
     * @param title                название книги
     * @param athorFullName        полное имя автора книги
     * @param isNew                является ли книга новой
     * @param releaseDate          дата выпуска книги
     * @param avaliabilityDateTime время получения книги
     */
    public Book(String title, String athorFullName, boolean isNew,
                LocalDate releaseDate, LocalDateTime avaliabilityDateTime) {
        this.title = title;
        this.athorFullName = athorFullName;
        this.isNew = isNew;
        this.releaseDate = releaseDate;
        this.avaliabilityDateTime = avaliabilityDateTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAthorFullName(String athorFullName) {
        this.athorFullName = athorFullName;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAvaliabilityDateTime(LocalDateTime avaliabilityDateTime) {
        this.avaliabilityDateTime = avaliabilityDateTime;
    }
}
