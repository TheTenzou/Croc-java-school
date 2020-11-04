package ru.TheTenzou.croc.java.school.Lecture4.task1.film;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * Актер.
 */
public class Actor {
    /**
     * Полное имя актера.
     */
    @XmlAttribute(name = "name")
    private String name;
    /**
     * Возрас актера.
     */
    @XmlAttribute(name = "age")
    private int age;
    /**
     * Роль актара.
     */
    @XmlAttribute(name = "role")
    private String role;

    /**
     * Конструктор.
     *
     * @param name полное имя
     * @param age  возраст
     * @param role роль
     */
    public Actor(String name, int age, String role) {
        this.name = name;
        this.age = age;
        this.role = role;
    }

    /**
     * Пустой конструктор.
     */
    public Actor() {
        this("Unnamed", 0, "None");
    }

    /**
     * Возрашает полное имя актера.
     *
     * @return полное имя актера
     */
    public String getName() {
        return name;
    }

    /**
     * Возврашает возрас актера.
     *
     * @return возраст
     */
    public int getAge() {
        return age;
    }

    /**
     * Возрашает роль актера.
     *
     * @return роль
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
        Actor actor = (Actor) o;
        return age == actor.age &&
                Objects.equals(name, actor.name) &&
                Objects.equals(role, actor.role);
    }

    /**
     * Возврашает хешкод объекта.
     *
     * @return заначение хешкода
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, age, role);
    }
}
