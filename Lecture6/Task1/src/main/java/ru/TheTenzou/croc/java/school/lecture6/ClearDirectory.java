package ru.TheTenzou.croc.java.school.lecture6;

import java.io.File;

/**
 * Класс зарускаощий поток, который запускает очишения файлов.
 */
public class ClearDirectory {

    /**
     * Поток очишаюший файлы.
     */
    private Thread thread;

    /**
     * Конструктор.
     *
     * @param file         очишаемая директория
     * @param timeInterval времменой промежуток
     */
    public ClearDirectory(File file, int timeInterval) {
        ClearDirectoryRunnable clearDirectoryRunnable = new ClearDirectoryRunnable(file, timeInterval);
        this.thread = new Thread(clearDirectoryRunnable);
        this.thread.setDaemon(true);
    }

    /**
     * Запустить поток.
     */
    public void start() {
        this.thread.start();
    }

    /**
     * Останвит поток.
     */
    public void stop() throws InterruptedException {
        this.thread.interrupt();
        // ждем пока поток завершится
        this.thread.join();
    }
}
