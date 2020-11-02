package ru.croc.java.school.lecture4.task1.film;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

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

    public Actor () {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return age == actor.age &&
                Objects.equals(name, actor.name) &&
                Objects.equals(role, actor.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, role);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", role='" + role + '\'' +
                '}';
    }
}
