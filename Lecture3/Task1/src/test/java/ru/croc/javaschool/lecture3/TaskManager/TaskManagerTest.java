package ru.croc.javaschool.lecture3.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.lecture3.TaskManager.Task.Performer;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;

import java.util.ArrayList;
import java.util.UUID;

public class TaskManagerTest {

    @Test
    void testAddTask() {
        TaskManager taskManager = new TaskManager(new ArrayList<>(), new ArrayList<>());

        assertEquals(new ArrayList<Task>(), taskManager.getTasks());

        Task task = new Task("name", "desc", null);

        taskManager.addTask(task);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);
        assertEquals( tasks, taskManager.getTasks());

    }

    @Test
    void testAddPerformer() {
        TaskManager taskManager = new TaskManager(new ArrayList<>(), new ArrayList<>());

        assertEquals(new ArrayList<Performer>(), taskManager.getPerformers());

        Performer performer = new Performer("firstName", "secondName");

        taskManager.addPerformer(performer);

        ArrayList<Performer> performers = new ArrayList<>();
        performers.add(performer);

        assertEquals(performers, taskManager.getPerformers());
    }

    @Test
    void testGetTask() {
        TaskManager taskManager = new TaskManager(new ArrayList<>(), new ArrayList<>());

        assertEquals(new ArrayList<Task>(), taskManager.getTasks());

        Task task = new Task("name", "desc", null);

        UUID taskCode = task.getCode();

        assertEquals(null,taskManager.getTask(taskCode));

        taskManager.addTask(task);

        assertEquals(task, taskManager.getTask(taskCode));
    }

    @Test
    void testGetPerformer() {
        TaskManager taskManager = new TaskManager(new ArrayList<>(), new ArrayList<>());

        assertEquals(new ArrayList<Task>(), taskManager.getTasks());

        Performer performer = new Performer("firstName", "secondName");

        UUID performerId = performer.getId();

        assertEquals(null, taskManager.getPerformer(performerId));

        taskManager.addPerformer(performer);

        assertEquals(performer, taskManager.getPerformer(performerId));

    }

    @Test
    void testRemoveTask() {
        TaskManager taskManager = new TaskManager(new ArrayList<>(), new ArrayList<>());

        Task task = new Task("name", "desc", null);
        UUID taskCode = task.getCode();

        taskManager.addTask(task);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);
        assertEquals(tasks, taskManager.getTasks());

        taskManager.removeTask(taskCode);

        assertEquals(new ArrayList<Task>(), taskManager.getTasks());
    }

    @Test
    void testRemovePerformer() {
        TaskManager taskManager = new TaskManager(new ArrayList<>(), new ArrayList<>());

        Performer performer = new Performer("firstName", "secondName");
        UUID performerId = performer.getId();

        taskManager.addPerformer(performer);
        ArrayList<Performer> performers = new ArrayList<>();
        performers.add(performer);

        assertEquals(performers, taskManager.getPerformers());

        taskManager.removePerformer(performerId);

        assertEquals(new ArrayList<Performer>(), taskManager.getPerformers());
    }
}
