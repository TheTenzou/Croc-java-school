package ru.croc.java.school.lecture4.task1.actor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "actors")
public class Actors {
    /**
     * Список актеров с их списком их ролей.
     */
    @XmlElement(name = "actor")
    private List<ActorWithFilms> actors;

    /**
     * Конструктор.
     * @param actors список актеров
     */
    public Actors(List<ActorWithFilms> actors) {
        this.actors = actors;
    }

    /**
     * Пустой конструктор.
     */
    public Actors() {

    }

    /**
     * Возврашает список актеров.
     * @param actors список актеров
     */
    public void setActors(List<ActorWithFilms> actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actors actors1 = (Actors) o;
        return Objects.equals(actors, actors1.actors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actors);
    }
}
