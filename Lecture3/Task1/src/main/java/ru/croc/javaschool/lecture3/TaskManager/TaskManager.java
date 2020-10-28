package ru.croc.javaschool.lecture3.TaskManager;

import ru.croc.javaschool.lecture3.TaskManager.Task.Performer;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * Система ведеия задачь.
 */
public class TaskManager {

    /**
     * Список задачь.
     */
    private ArrayList<Task> tasks;

    /**
     * Список исполнителей.
     */
    private ArrayList<Performer> performers;

    /**
     * Котсруктор.
     *
     * @param tasks      список задачь
     * @param performers список исполнителей
     */
    public TaskManager(Collection<Task> tasks, Collection<Performer> performers) {
        this.tasks = new ArrayList<>(tasks);
        this.performers = new ArrayList<>(performers);
    }

    /**
     * Дабавть задачу.
     *
     * @param task задача
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Даобавть исполнителя.
     *
     * @param performer исполнитель
     */
    public void addPerformer(Performer performer) {
        this.performers.add(performer);
    }

    /**
     * Возврашает задачу.
     *
     * @param code код задачи
     * @return задача
     */
    public Task getTask(UUID code) {
        for (Task task : tasks) {
            if (task.getCode() == code) {
                return task;
            }
        }
        return null;
    }

    /**
     * Возврашает исполнителя.
     *
     * @param id индентификатор
     * @return исполнитель
     */
    public Performer getPerformer(UUID id) {
        for (Performer executor : performers) {
            if (executor.getId() == id) {
                return executor;
            }
        }
        return null;
    }

    /**
     * Возврашает задачу.
     *
     * @param code код задачи
     */
    public void removeTask(UUID code) {
        tasks.removeIf(task -> task.getCode() == code);
    }

    /**
     * Возврашает исполнителя.
     *
     * @param id индентификатор
     */
    public void removePerformer(UUID id) {
        performers.removeIf(performer -> performer.getId() == id);
    }

    /**
     * Возрашает список задачь.
     *
     * @return спимок задачь
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Возврашает список исполнителей.
     *
     * @return список исполнителей
     */
    public ArrayList<Performer> getPerformers() {
        return performers;
    }

    @Override
    public String toString() {
        return "TaskManager{" +
                "tasks=" + tasks +
                ", performers=" + performers +
                '}';
    }
}
