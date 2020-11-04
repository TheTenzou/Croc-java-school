package ru.TheTenzou.croc.java.school.Lecture4.task1.film;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Objects;

/**
 * Список фильмов.
 */
@XmlRootElement(name = "films")
public class Films {
    /**
     * Список филмов.
     */
    @XmlElement(name = "film")
    private List<FilmWithActors> films;

    /**
     * Конструктор.
     *
     * @param films список фильмов
     */
    public Films(List<FilmWithActors> films) {
        this.films = films;
    }

    public Films() {

    }

    /**
     * Возврашает список фильмов.
     *
     * @return список фильмов
     */
    public List<FilmWithActors> getFilms() {
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
        Films films1 = (Films) o;
        return Objects.equals(films, films1.films);
    }

    /**
     * Возврашает хешкод объекта.
     *
     * @return заначение хешкода
     */
    @Override
    public int hashCode() {
        return Objects.hash(films);
    }
}
