package ru.croc.java.school.lecture4.task1.actor;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * Фильм и роль актера.
 */
public class Film {
    /**
     * Название фильма.
     */
    @XmlAttribute
    private String title;
    /**
     * Роль актера.
     */
    @XmlAttribute
    private String role;

    /**
     * Коструктор.
     *
     * @param title название фильма
     * @param role  название роли актера
     */
    public Film(String title, String role) {
        this.title = title;
        this.role = role;
    }

    /**
     * Пустой конструктор.
     */
    public Film() {
        this("Untitled", "None");
    }

    /**
     * Возварашет название фильма.
     *
     * @return название фильма
     */
    public String getTitle() {
        return title;
    }

    /**
     * Возврашает название роли.
     *
     * @return название роли
     */
    public String getRole() {
        return role;
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
        Film film = (Film) o;
        return Objects.equals(title, film.title) &&
                Objects.equals(role, film.role);
    }

    /**
     * Возврашает хешкод объекта.
     *
     * @return заначение хешкода
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, role);
    }
}
