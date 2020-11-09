package ru.croc.javaschool.lecture3.TaskManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.lecture3.Application;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;
import ru.croc.javaschool.lecture3.utils.ReadWriteObjects;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationTest {

    @Test
    public void testHelpAndExit() {
        // Симулируем вод с пользователя.
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("help\r\nexit\r\n".getBytes());
        System.setIn(in);

        String[] filesName = new String[2];
        filesName[0] = "TestConsoleTask.out";
        filesName[1] = "TestConsole.out";
        Application.main(filesName);

        File taskFile = new File(filesName[0]);
        File performersFile = new File(filesName[1]);
        taskFile.delete();
        performersFile.delete();

        System.setIn(sysInBackup);
    }

    @Test
    public void testAddTask() {
        Task task = new Task("task", "desc", null);

        // Симулируем вод с пользователя.
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("add task\r\n" +
                task.getName() + "\r\n" +
                task.getDescription() + "\r\n" +
                "\r\n" +
                "д\r\n" +
                "save\r\n" +
                "exit\r\n").getBytes());
        System.setIn(in);


        String[] filesName = new String[2];
        filesName[0] = "TestConsoleTask.out";
        filesName[1] = "TestConsole.out";

        Application.main(filesName);

        List<Task> taskList = ReadWriteObjects.readTask(filesName[0]);
        List<Task> expectedTaskList = Arrays.asList(task);

        // Объект task который создан в тесте и объект который создан при ваолнении команды ни разные
        // и  поле code у них разный так это поле генирируется рандомно
        for (int i = 0; i < taskList.size(); ++i) {
            Assertions.assertEquals(expectedTaskList.get(i).getName(), taskList.get(i).getName());
            Assertions.assertEquals(expectedTaskList.get(i).getDescription(), taskList.get(i).getDescription());
            Assertions.assertEquals(expectedTaskList.get(i).getState(), taskList.get(i).getState());
            Assertions.assertEquals(expectedTaskList.get(i).getPerformer(), taskList.get(i).getPerformer());
        }

        // удаляем файлы
        File taskFile = new File(filesName[0]);
        File performersFile = new File(filesName[1]);
        taskFile.delete();
        performersFile.delete();

        System.setIn(sysInBackup);
    }

}
