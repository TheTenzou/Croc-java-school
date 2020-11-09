package ru.croc.javaschool.lecture3.TaskManager;

import org.junit.jupiter.api.*;
import ru.croc.javaschool.lecture3.Application;
import ru.croc.javaschool.lecture3.TaskManager.Task.Performer;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;
import ru.croc.javaschool.lecture3.utils.ReadWriteObjects;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Проверка работы консоли.
 */
public class ApplicationTest {

    /**
     * массив с названиями файлов для тестов.
     */
    private static String[] filesName;

    /**
     * Конец стороки.
     */
    private final String endL = System.lineSeparator();

    /**
     * Стандартый вывод.
     */
    private final PrintStream sysOut = System.out;

    /**
     * Название файлов для тестов.
     */
    @BeforeAll
    public static void setFileNames() {
        filesName = new String[2];
        filesName[0] = "TestConsoleTask.out";
        filesName[1] = "TestConsole.out";
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    /**
     * Удаление тестовых файлов.
     */
    @AfterEach
    public void deleteFiles(){
        File taskFile = new File(filesName[0]);
        File performersFile = new File(filesName[1]);
        taskFile.delete();
        performersFile.delete();
        System.setOut(sysOut);
    }

    /**
     * Проверка добаление задачи.
     */
    @Test
    @DisplayName("Проверка добаление задачи")
    public void testAddTask() {
        Task task = new Task("task", "desc", null);

        // Симулируем вод с пользователя.
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("add task" + endL +
                task.getName() + endL +
                task.getDescription() + endL +
                endL +
                "д" + endL +
                "save" + endL +
                "exit" + endL).getBytes());
        System.setIn(in);

        Application.main(filesName);

        List<Task> taskList = ReadWriteObjects.readTask(filesName[0]);
        List<Task> expectedTaskList = Arrays.asList(task);

        // Объект task который создан в тесте и объект который создан при ваолнении команды ни разные
        // и  поле code у них разный так это поле генирируется рандомно
        Assertions.assertEquals(expectedTaskList.size(), taskList.size());
        for (int i = 0; i < taskList.size(); ++i) {
            Assertions.assertEquals(expectedTaskList.get(i).getName(), taskList.get(i).getName());
            Assertions.assertEquals(expectedTaskList.get(i).getDescription(), taskList.get(i).getDescription());
            Assertions.assertEquals(expectedTaskList.get(i).getState(), taskList.get(i).getState());
            Assertions.assertEquals(expectedTaskList.get(i).getPerformer(), taskList.get(i).getPerformer());
        }

        System.setIn(sysInBackup);
    }

    /**
     * Проверка добовления исполнителя.
     */
    @Test
    @DisplayName("Проверка добовления исполнителя")
    public void testAddPerformer() {
        Performer performer = new Performer("FirstName", "SecondName");

        // Симулируем вод с пользователя.
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("add performer" + endL +
                performer.getFirstName() + endL +
                performer.getLastName() + endL +
                "save" + endL +
                "exit" + endL).getBytes());
        System.setIn(in);


        String[] filesName = new String[2];
        filesName[0] = "TestConsoleTask.out";
        filesName[1] = "TestConsole.out";

        Application.main(filesName);

        List<Performer> performers = ReadWriteObjects.readPerformers(filesName[1]);
        List<Performer> expectedTaskList = Arrays.asList(performer);

        // Объект performer который создан в тесте и объект который создан при ваолнении команды ни разные
        // и  поле id у них разный так это поле генирируется рандомно
        Assertions.assertEquals(expectedTaskList.size(), performers.size());
        for (int i = 0; i < performers.size(); ++i) {
            Assertions.assertEquals(expectedTaskList.get(i).getFirstName(), performers.get(i).getFirstName());
            Assertions.assertEquals(expectedTaskList.get(i).getLastName(), performers.get(i).getLastName());
        }

        System.setIn(sysInBackup);
    }

    /**
     * Проверка удаления задачи.
     */
    @Test
    @DisplayName("Проверка удаления задачи")
    public void testDeleteTask() throws IOException {

        Task task1 = new Task("task1", "desc1", null);
        Task task2 = new Task("task2", "desc2", null);
        List<Task> expectedTaskList = Arrays.asList(task1, task2);

        ReadWriteObjects.writeObjects(expectedTaskList, filesName[0]);

        // Симулируем вод с пользователя.
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("delete task " +
                task1.getCode() + endL +
                "save" + endL +
                "exit" + endL).getBytes());
        System.setIn(in);

        Application.main(filesName);

        expectedTaskList = Arrays.asList(task2);
        List<Task> taskList = ReadWriteObjects.readTask(filesName[0]);

        // Объект task который создан в тесте и объект который создан при ваолнении команды ни разные
        // и  поле code у них разный так это поле генирируется рандомно
        Assertions.assertEquals(expectedTaskList.size(), taskList.size());
        for (int i = 0; i < taskList.size(); ++i) {
            Assertions.assertEquals(expectedTaskList.get(i).getName(), taskList.get(i).getName());
            Assertions.assertEquals(expectedTaskList.get(i).getDescription(), taskList.get(i).getDescription());
            Assertions.assertEquals(expectedTaskList.get(i).getState(), taskList.get(i).getState());
            Assertions.assertEquals(expectedTaskList.get(i).getPerformer(), taskList.get(i).getPerformer());
        }

        System.setIn(sysInBackup);
    }

    /**
     * Проверка удаления исполнителя.
     */
    @Test
    @DisplayName("Проверка удаления исполнителя")
    public void testDeletePerformer() throws IOException {

        Performer performer1 = new Performer("firstName1", "SecondName1");
        Performer performer2 = new Performer("firstName2", "SecondName2");
        List<Performer> expectedTaskList = Arrays.asList(performer1, performer2);

        ReadWriteObjects.writeObjects(expectedTaskList, filesName[1]);

        // Симулируем вод с пользователя.
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("delete performer " +
                performer1.getId() + endL +
                "save" + endL +
                "exit" + endL).getBytes());
        System.setIn(in);

        Application.main(filesName);

        expectedTaskList = Arrays.asList(performer2);
        List<Performer> taskList = ReadWriteObjects.readPerformers(filesName[1]);

        // Объект task который создан в тесте и объект который создан при ваолнении команды ни разные
        // и  поле code у них разный так это поле генирируется рандомно
        Assertions.assertEquals(expectedTaskList.size(), taskList.size());
        for (int i = 0; i < taskList.size(); ++i) {
            Assertions.assertEquals(expectedTaskList.get(i).getFirstName(), taskList.get(i).getFirstName());
            Assertions.assertEquals(expectedTaskList.get(i).getLastName(), taskList.get(i).getLastName());
        }

        System.setIn(sysInBackup);
    }

    /**
     * Проверка завершения задачи.
     */
    @Test
    @DisplayName("Проверка завершения задачи")
    public void testCompleteTask() throws IOException {

        Task task1 = new Task("task1", "desc1", null);
        Task task2 = new Task("task2", "desc2", null);
        List<Task> expectedTaskList = Arrays.asList(task1, task2);

        ReadWriteObjects.writeObjects(expectedTaskList, filesName[0]);

        // Симулируем вод с пользователя.
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("complete task " +
                task1.getCode() + endL +
                "save" + endL +
                "exit" + endL).getBytes());
        System.setIn(in);

        Application.main(filesName);

        task1.complete();
        List<Task> taskList = ReadWriteObjects.readTask(filesName[0]);

        // Объект task который создан в тесте и объект который создан при ваолнении команды ни разные
        // и  поле code у них разный так это поле генирируется рандомно
        Assertions.assertEquals(expectedTaskList.size(), taskList.size());
        for (int i = 0; i < taskList.size(); ++i) {
            Assertions.assertEquals(expectedTaskList.get(i).getName(), taskList.get(i).getName());
            Assertions.assertEquals(expectedTaskList.get(i).getDescription(), taskList.get(i).getDescription());
            Assertions.assertEquals(expectedTaskList.get(i).getState(), taskList.get(i).getState());
            Assertions.assertEquals(expectedTaskList.get(i).getPerformer(), taskList.get(i).getPerformer());
        }

        System.setIn(sysInBackup);
    }

    /**
     * Проверка назначения задачи исполнителя.
     */
    @Test
    @DisplayName("Проверка назначения задачи исполнителя")
    public void testAsignPerformerToTask() throws IOException {

        Task task1 = new Task("task1", "desc1", null);
        Task task2 = new Task("task2", "desc2", null);
        List<Task> expectedTaskList = Arrays.asList(task1, task2);

        Performer performer = new Performer("FirstName", "LastName");
        List<Performer> expecPerformers = Arrays.asList(performer);

        ReadWriteObjects.writeObjects(expectedTaskList, filesName[0]);
        ReadWriteObjects.writeObjects(expecPerformers, filesName[1]);

        // Симулируем вод с пользователя.
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("asign performer " +
                performer.getId() + " " +
                task1.getCode() + endL +
                "save" + endL +
                "exit" + endL).getBytes());
        System.setIn(in);

        Application.main(filesName);

        task1.setPerformer(performer);
        List<Task> taskList = ReadWriteObjects.readTask(filesName[0]);
        System.out.println(taskList);

        // Объект task который создан в тесте и объект который создан при ваолнении команды ни разные
        // и  поле code у них разный так это поле генирируется рандомно
        Assertions.assertEquals(expectedTaskList.size(), taskList.size());
        for (int i = 0; i < taskList.size(); ++i) {
            Assertions.assertEquals(expectedTaskList.get(i).getName(), taskList.get(i).getName());
            Assertions.assertEquals(expectedTaskList.get(i).getDescription(), taskList.get(i).getDescription());
            Assertions.assertEquals(expectedTaskList.get(i).getState(), taskList.get(i).getState());
            Assertions.assertEquals(expectedTaskList.get(i).getPerformer(), taskList.get(i).getPerformer());
        }

        System.setIn(sysInBackup);
    }
}
