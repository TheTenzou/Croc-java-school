package ru.TheTenzou.croc.java.school.lecture6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ClearDirectoryTest {

    /**
     * директория для тестов.
     */
    private static File temp_directory;

    @BeforeAll
    public static void initTestFilder() throws IOException {
        temp_directory = Files.createDirectory(Path.of("folderForTest")).toFile();
        temp_directory.deleteOnExit();

        File file = new File(temp_directory, "filename.txt");
        file.createNewFile();
    }

    @Test
    public void testClearDirectory() throws IOException {

        int timeInterval = 2;

        ClearDirectory clearDirectory = new ClearDirectory(temp_directory, timeInterval);
        clearDirectory.start();

        // ждем очестки папки
        while (temp_directory.listFiles().length != 0) { }
        LocalDateTime clearFirstTime = LocalDateTime.now();

        // создаем файл
        File file = new File(temp_directory, "test.txt");
        file.createNewFile();

        // ждем очестки папки
        while (temp_directory.listFiles().length != 0) { }
        LocalDateTime clearSecondTime = LocalDateTime.now();

        // создаем файл
        file = new File(temp_directory, "test2.txt");
        file.createNewFile();

        // ждем очестки папки
        while (temp_directory.listFiles().length != 0) { }
        LocalDateTime clearThirdTime = LocalDateTime.now();

        // время между очистками файлов
        long firstIntervalMiles = clearFirstTime.until(clearSecondTime, ChronoUnit.MILLIS);
        long secondIntervalMiles = clearSecondTime.until(clearThirdTime, ChronoUnit.MILLIS);
        // округляем время
        int firstIntervalSeconds = Math.round(firstIntervalMiles / 1_000f);
        int secondIntervalSeconds = Math.round(secondIntervalMiles / 1_000f);

        Assertions.assertEquals(timeInterval, firstIntervalSeconds);
        Assertions.assertEquals(timeInterval, secondIntervalSeconds);
    }

}
