package ru.TheTenzou.croc.java.school.Lecture6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Многопоточный поиск максимального елемента.
 *
 * @param <T>
 */
public class FindMaxMultythread<T extends Comparable<T>> {

    /**
     * Колличество потоков.
     */
    private final int threadCount;

    /**
     * Пул потоков.
     */
    private final ExecutorService executorService;

    /**
     * Конструктор.
     *
     * @param threadCount колличество потоков
     */
    public FindMaxMultythread(int threadCount) {
        this.threadCount = threadCount;
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    /**
     * Метод возрашаюший максимальный эжлемент колекции.
     *
     * @param collection колекция
     * @return максимальный элемет
     * @throws ExecutionException   ошибка при получении результата
     * @throws InterruptedException ошибка прирывания потока
     */
    public T findMax(List<T> collection) throws ExecutionException, InterruptedException {
        // если коллекция меньше чем коллччество потоков то нет смысла делить
        if (collection.size() <= threadCount) {
            return Collections.max(collection);
        }

        List<Future<T>> futures = new ArrayList<>(threadCount);
        List<List<T>> splitedColections = splitColletion(collection, threadCount);

        for (int i = 0; i < splitedColections.size(); i++) {
            Callable<T> maxInSplit = new FindMax<>(splitedColections.get(i));
            Future<T> future = executorService.submit(maxInSplit);
            futures.add(i, future);
        }

        System.out.println(Thread.activeCount());

        List<T> results = new ArrayList<>();
        for (Future<T> future : futures) {
            while (!future.isDone()) ;
            results.add(future.get());
        }

        executorService.shutdown();

        return Collections.max(results);
    }

    /**
     * Метод разделяюший коллекцию на несколько колекций.
     *
     * @param collection  колекция
     * @param splitsCount колличество честей
     * @return массив колеций
     */
    private List<List<T>> splitColletion(List<T> collection, int splitsCount) {

        int i = 0;
        List<List<T>> splitCollections = new ArrayList<>();
        while (i < collection.size()) {
            int nextInc = Math.min(collection.size() - i, splitsCount);
            List<T> batch = collection.subList(i, i + nextInc);
            splitCollections.add(batch);
            i = i + nextInc;
        }

        return splitCollections;
    }
}
