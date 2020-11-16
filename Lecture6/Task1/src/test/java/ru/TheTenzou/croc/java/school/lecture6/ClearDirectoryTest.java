package ru.TheTenzou.croc.java.school.lecture6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ClearDirectoryTest {

    /**
     * директория для тестов.
     */
    private static File temp_directory;

    /**
     * Создание директории.
     */
    @BeforeAll
    public static void initTestDirectory() throws IOException {
        temp_directory = new File("folderForTest");
        temp_directory.mkdir();
    }

    /**
     * Удалени папки и ее содерхимого.
     */
    @AfterAll
    public static void deleteTestDirectory() {
        deleteFolder(temp_directory);
    }

    /**
     * Метод удаляюший папку.
     *
     * @param directory удаляемая папка
     */
    private static void deleteFolder(File directory) {
        File[] directorys = directory.listFiles();
        if (directorys != null) {
            for (File file : directorys) {
                deleteFolder(file);
            }
        }
        directory.delete();
    }

    /**
     * Провека очиски директории.
     */
    @Test
    public void testClearDirectory() throws IOException {

        int timeInterval = 1;

        ClearDirectory clearDirectory = new ClearDirectory(temp_directory, timeInterval);
        clearDirectory.start();

        // ждем очестки папки
        while (temp_directory.listFiles().length != 0) {
        }
        LocalDateTime clearFirstTime = LocalDateTime.now();

        // создаем файл
        File file = new File(temp_directory, "test.txt");
        file.createNewFile();

        // ждем очестки папки
        while (temp_directory.listFiles().length != 0) {
        }
        LocalDateTime clearSecondTime = LocalDateTime.now();

        // создаем файл
        file = new File(temp_directory, "test2.txt");
        file.createNewFile();

        // ждем очестки папки
        while (temp_directory.listFiles().length != 0) {
        }
        LocalDateTime clearThirdTime = LocalDateTime.now();

        // время между очистками файлов
        long firstIntervalMiles = clearFirstTime.until(clearSecondTime, ChronoUnit.MILLIS);
        long secondIntervalMiles = clearSecondTime.until(clearThirdTime, ChronoUnit.MILLIS);
        // округляем время
        int firstIntervalSeconds = Math.round(firstIntervalMiles / 1_000f);
        int secondIntervalSeconds = Math.round(secondIntervalMiles / 1_000f);

        Assertions.assertEquals(timeInterval, firstIntervalSeconds);
        Assertions.assertEquals(timeInterval, secondIntervalSeconds);

        clearDirectory.stop();
    }

    /**
     * Провека удаления вложеных папок.
     */
    @Test
    public void testNestedDirectories() throws IOException {

        int timeInterval = 2;

        ClearDirectory clearDirectory = new ClearDirectory(temp_directory, timeInterval);
        clearDirectory.start();

        // ждем очестки папки
        while (temp_directory.listFiles().length != 0) {
        }
        LocalDateTime clearFirstTime = LocalDateTime.now();

        // создаем файл
        File inerDirectory = new File(temp_directory, "inerDirectory");
        inerDirectory.mkdir();
        File file = new File(inerDirectory, "test.txt");
        file.createNewFile();
        File emptyInerDirectory = new File(temp_directory, "empty");
        emptyInerDirectory.mkdir();

        // ждем очестки папки
        while (temp_directory.listFiles().length != 0) {
        }
        LocalDateTime clearSecondTime = LocalDateTime.now();

        long miles = clearFirstTime.until(clearSecondTime, ChronoUnit.MILLIS);
        int seconds = Math.round(miles / 1_000f);

        Assertions.assertEquals(timeInterval, seconds);

        clearDirectory.stop();
    }
}
