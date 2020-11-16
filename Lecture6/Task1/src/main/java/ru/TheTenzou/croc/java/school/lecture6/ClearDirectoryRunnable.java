package ru.TheTenzou.croc.java.school.lecture6;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * Поток очишаюший директорию.
 */
public class ClearDirectoryRunnable implements Runnable {

    /**
     * Путь к очишаемой директории.
     */
    private File directory;

    /**
     * Временной интервал в млисекуедах.
     */
    private int timeInterval;

    /**
     * Конструктор.
     *
     * @param directory    директория
     * @param timeInterval интервал в секундах
     */
    public ClearDirectoryRunnable(File directory, int timeInterval) {
        this.directory = directory;
        this.timeInterval = timeInterval * 1_000;
    }

    /**
     * Метод удаляюший фалый в заданной директории.
     */
    @Override
    public void run() {

        for (; ; ) {
            if (!Thread.currentThread().isInterrupted()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        deleteDirectory(file);
                    }
                }
                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void deleteDirectory(File directory) {
        File[] paths = directory.listFiles();
        if (paths != null) {
            for (File file : paths) {
                deleteDirectory(file);
            }
        }
        directory.delete();
    }
}
