package ru.croc.java.school.lecture4.task1.film;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "film")
public class FilmWithActors {
    /**
     * Название фильма.
     */
    @XmlElement(name = "title")
    private String title;
    /**
     * Описмние фильма.
     */
    @XmlElement(name = "description")
    private String description;
    /**
     * Список актеров с из ролями.
     */
    @XmlElementWrapper(name = "actors")
    @XmlElement(name = "actor")
    private List<Actor> actors;

    /**
     * Конструктор.
     *
     * @param title       название
     * @param description описание
     * @param actors      актеры
     */
    public FilmWithActors(String title, String description, List<Actor> actors) {
        this.title = title;
        this.description = description;
        this.actors = actors;
    }

    public FilmWithActors() {

    }

    /**
     * Возврашет название фильма.
     *
     * @return название фильма
     */
    public String getTitle() {
        return title;
    }

    /**
     * Возрашает описание фильма.
     *
     * @return описание фильма
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возрашает список актеров.
     *
     * @return список актеров
     */
    public List<Actor> getActors() {
        return actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmWithActors that = (FilmWithActors) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(actors, that.actors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, actors);
    }

    @Override
    public String toString() {
        return "FilmWithActors{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", actors=" + actors +
                '}';
    }
}
