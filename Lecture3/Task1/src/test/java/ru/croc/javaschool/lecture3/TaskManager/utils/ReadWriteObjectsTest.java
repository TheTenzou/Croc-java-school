package ru.croc.javaschool.lecture3.TaskManager.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.lecture3.TaskManager.Task.Performer;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;
import ru.croc.javaschool.lecture3.utils.ReadWriteObjects;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReadWriteObjectsTest {

    /**
     * Проверка чтения изаписи задач.
     *
     * @throws IOException ошибка работы с файлом.
     */
    @Test
    @DisplayName("Проверка чтения изаписи задач")
    public void testReadWriteTasks() throws IOException {
        List<Task> tasks = Arrays.asList(
                new Task("task1", "Description1", null),
                new Task("task2", "Description2", null)
        );
        String tasksFileName = "TestTask.out";
        ReadWriteObjects.writeObjects(tasks, tasksFileName);
        List<Task> tasksFromFile = ReadWriteObjects.readTask(tasksFileName);

        File file = new File(tasksFileName);
        file.delete();

        Assertions.assertEquals(tasks, tasksFromFile);
    }

    /**
     * Проверка чтения изаписи сполнителей.
     *
     * @throws IOException ошибка работы с файлом.
     */
    @Test
    @DisplayName("Проверка чтения изаписи сполнителей")
    public void testReadWritePerformers() throws IOException {
        List<Performer> performers = Arrays.asList(
                new Performer("firstName1", "SecondName1"),
                new Performer("firstName2", "SecondName2")
        );
        String performersFileName = "TestPerformers.out";
        ReadWriteObjects.writeObjects(performers, performersFileName);
        List<Performer> performersFromFile = ReadWriteObjects.readPerformers(performersFileName);

        File file = new File(performersFileName);
        file.delete();

        Assertions.assertEquals(performers, performersFromFile);
    }
}
