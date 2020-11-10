package ru.TheTenzou.croc.java.school.Lecture5.Task.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
     * @param id                   индетификатор книги
     * @param title                название книги
     * @param athorFullName        полное имя автора книги
     * @param isNew                является ли книга новой
     * @param releaseDate          дата выпуска книги
     * @param avaliabilityDateTime время получения книги
     */
    public Book(int id, String title, String athorFullName, boolean isNew,
                LocalDate releaseDate, LocalDateTime avaliabilityDateTime) {
        this.id = id;
        this.title = title;
        this.athorFullName = athorFullName;
        this.isNew = isNew;
        this.releaseDate = releaseDate;
        this.avaliabilityDateTime = avaliabilityDateTime;
    }

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
        this(-1, title, athorFullName, isNew, releaseDate, avaliabilityDateTime);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                isNew == book.isNew &&
                Objects.equals(title, book.title) &&
                Objects.equals(athorFullName, book.athorFullName) &&
                Objects.equals(releaseDate, book.releaseDate) &&
                Objects.equals(avaliabilityDateTime, book.avaliabilityDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, athorFullName, isNew, releaseDate, avaliabilityDateTime);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", athorFullName='" + athorFullName + '\'' +
                ", isNew=" + isNew +
                ", releaseDate=" + releaseDate +
                ", avaliabilityDateTime=" + avaliabilityDateTime +
                '}';
    }
}
