package ru.croc.javaschool.lecture3.TaskManager.Task;

import java.io.Serializable;
import java.util.UUID;

/**
 * Исполнитель.
 */
public class Performer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор исполнителя.
     */
    private final UUID id;

    /**
     * Имя исполнителя.
     */
    private final String firstName;

    /**
     * Фамилия исполнителя.
     */
    private final String lastName;

    /**
     * Кнструктор исполнителя.
     *
     * @param firstName Имя исполнителя
     * @param lastName  Фамилия исполнителя
     */
    public Performer(String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Возврашает индетификатор исполнителя.
     * @return индетификатор
     */
    public UUID getId() {
        return id;
    }

    /**
     * Возврашает имя сполнителя.
     *
     * @return Имя исполнителя
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Возврашант фамилию исполнителя.
     *
     * @return Фамилия исполнителя
     */
    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Performer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
