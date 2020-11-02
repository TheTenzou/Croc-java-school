package ru.croc.java.school.lecture4.task1.actor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Актер со спском фильмов.
 */
@XmlRootElement(name = "actor")
public class ActorWithFilms {
    /**
     * Полное имя актера.
     */
    @XmlElement
    private String name;
    /**
     * Возраст актера.
     */
    @XmlElement
    private int age;
    /**
     * Список фильмов.
     */
    @XmlElementWrapper(name = "films")
    @XmlElement(name = "film")
    private List<Film> films;

    /**
     * Конструктор.
     *
     * @param name  полное имя актера
     * @param age   возраст актера
     * @param films список фильмов
     */
    public ActorWithFilms(String name, int age, List<Film> films) {
        this.name = name;
        this.age = age;
        this.films = films;
    }

    public ActorWithFilms() {

    }

    /**
     * Возврашает полное имя актера.
     *
     * @return полное имя актера
     */
    public String getName() {
        return name;
    }

    /**
     * Возврашает возраст актера.
     *
     * @return возраст фктера
     */
    public int getAge() {
        return age;
    }

    /**
     * Возврашает список фильмов.
     *
     * @return список фольмов
     */
    public List<Film> getFilms() {
        return films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorWithFilms that = (ActorWithFilms) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(films, that.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, films);
    }
}
