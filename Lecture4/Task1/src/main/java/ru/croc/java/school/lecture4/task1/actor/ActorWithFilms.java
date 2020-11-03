package ru.croc.java.school.lecture4.task1.actor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
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

    /**
     * Конструктор актера без фильмов.
     *
     * @param name полное имя
     * @param age  возраст
     */
    public ActorWithFilms(String name, int age) {
        this(name, age, new ArrayList<>());
    }

    /**
     * Пустой конструктор.
     */
    public ActorWithFilms() {
        this("UnNamed", 0);
    }

    /**
     * Добавить фильм в список фильмов.
     *
     * @param film фильм
     */
    public void addFilm(Film film) {
        this.films.add(film);
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

    /**
     * Проверка являются ли обекты одинакавыми.
     *
     * @param o сравниваемый объект
     * @return true если объекты одинаковые; false если разные
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorWithFilms that = (ActorWithFilms) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(films, that.films);
    }

    /**
     * Возврашает хешкод объекта.
     *
     * @return заначение хешкода
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, age, films);
    }
}
