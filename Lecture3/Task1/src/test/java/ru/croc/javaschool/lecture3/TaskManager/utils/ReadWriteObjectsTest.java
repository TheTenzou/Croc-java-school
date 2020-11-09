package ru.croc.javaschool.lecture3.TaskManager.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.lecture3.TaskManager.Task.Performer;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;
import ru.croc.javaschool.lecture3.utils.ReadWriteObjects;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReadWriteObjectsTest {

    @Test
    public void testReadWriteTasks() throws IOException {
        List<Task> tasks = Arrays.asList(
                new Task("task1", "Description1", null),
                new Task("task2", "Description2", null)
        );
        String taskFileNameTest = "TestTask.out";
        ReadWriteObjects.writeObjects(tasks, taskFileNameTest);
        List<Task> tasksFromFile = ReadWriteObjects.readTask(taskFileNameTest);

        Assertions.assertEquals(tasks, tasksFromFile);
    }

    @Test
    public void testReadWriteTask() throws IOException {
        List<Performer> performers = Arrays.asList(
                new Performer("firstName1", "SecondName1"),
                new Performer("firstName2", "SecondName2")
        );
        String taskFileNameTest = "TestPerformers.out";
        ReadWriteObjects.writeObjects(performers, taskFileNameTest);
        List<Performer> performersFromFile = ReadWriteObjects.readPerformers(taskFileNameTest);

        Assertions.assertEquals(performers, performersFromFile);
    }
}
