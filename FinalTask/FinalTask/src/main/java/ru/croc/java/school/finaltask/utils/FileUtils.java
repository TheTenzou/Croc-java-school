package ru.croc.java.school.finaltask.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Утилитарый класс для сохранения данных в файл.
 */
public class FileUtils {

    /**
     * Метод сохраняюший строку в файл.
     * @param file файл в который будет сохранена строка
     * @param str строка
     * @throws IOException ошибки при соохранении файла
     */
    public static void saveString(File file, String str) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(str);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
