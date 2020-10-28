package ru.croc.javaschool.lecture3.TaskManager.Task;

import java.io.Serializable;
import java.util.UUID;

/**
 * Задача.
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Код задачи.
     */
    private final UUID code;

    /**
     * Наименование.
     */
    private final String name;

    /**
     * Описание.
     */
    private final String description;

    /**
     * Исполнитель.
     */
    private Performer performer;

    /**
     * Статус.
     * true - завершен
     * false - не завершен
     */
    private boolean state;

    /**
     * Конструктор.
     *
     * @param name        Наименование
     * @param description Описание
     * @param executor    Исполнитель
     */
    public Task(String name, String description, Performer executor) {
        this.code = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.performer = executor;
        this.state = false;
    }

    /**
     * Изменить статус задачи на изменить.
     */
    public void complete() {
        this.state = true;
    }

    /**
     * Установть исполнителя.
     *
     * @param performer сполнитель
     */
    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    /**
     * Возврашает код задачи.
     *
     * @return код задачи
     */
    public UUID getCode() {
        return code;
    }

    /**
     * Возврашает наименование задачи.
     *
     * @return наименование
     */
    public String getName() {
        return name;
    }

    /**
     * Возврашает описпние задачи.
     *
     * @return описание
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возврашает исполнителя задачи.
     *
     * @return исполнитель
     */
    public Performer getPerformer() {
        return performer;
    }

    /**
     * Возврашает состояние задачи.
     *
     * @return состояние
     */
    public boolean getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", performer=" + performer +
                ", status=" + state +
                '}';
    }
}
