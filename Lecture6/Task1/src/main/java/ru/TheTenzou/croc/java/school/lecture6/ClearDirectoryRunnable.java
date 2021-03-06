package ru.TheTenzou.croc.java.school.lecture6;

import java.io.File;

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
                    break;
                }
            } else {
                break;
            }
        }
    }

    /**
     * Удаление директории и всех вложеных файлов.
     *
     * @param directory директория
     */
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
