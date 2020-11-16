package ru.TheTenzou.croc.java.school.Lecture6;

import java.util.ArrayList;
import java.util.Collection;
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
    public T findMax(Collection<T> collection) throws ExecutionException, InterruptedException {
        // если коллекция меньше чем коллччество потоков то нет смысла делить
        if (collection.size() <= threadCount) {
            return Collections.max(collection);
        }

        List<Future<T>> futures = new ArrayList<>(threadCount);
        ArrayList<Collection<T>> splitedColections = splitColletion(collection, threadCount);

        for (int i = 0; i < splitedColections.size(); i++) {
            Callable<T> maxInSplit = new FindMax<>(splitedColections.get(i));
            Future<T> future = executorService.submit(maxInSplit);
            futures.add(i, future);
        }

        List<T> results = new ArrayList<>();
        for (Future<T> future : futures) {
            while (!future.isDone()) ;
            results.add(future.get());
        }
        return Collections.max(results);
    }

    /**
     * Метод разделяюший коллекцию на несколько колекций.
     *
     * @param collection  колекция
     * @param splitsCount колличество честей
     * @return массив колеций
     */
    private ArrayList<Collection<T>> splitColletion(Collection<T> collection, int splitsCount) {
        ArrayList<Collection<T>> splits = new ArrayList<>(splitsCount);
        int splitSize = Math.round(collection.size() / splitsCount);

        int currentSplit = 0;
        int currentElement = 0;
        splits.add(new ArrayList<>());
        for (T element : collection) {
            if (currentElement >= splitSize) {
                currentSplit++;
                splits.add(new ArrayList<>());
                currentElement = 0;
                splits.get(currentSplit).add(element);
            }
            else {
                splits.get(currentSplit).add(element);
            }
            currentElement++;
        }

        return splits;
    }
}
