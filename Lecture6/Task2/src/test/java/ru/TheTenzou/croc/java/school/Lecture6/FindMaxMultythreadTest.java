package ru.TheTenzou.croc.java.school.Lecture6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class FindMaxMultythreadTest {

    /**
     * Проверка нахождения максимума.
     */
    @Test
    public void testFindMax() throws ExecutionException, InterruptedException {
        List<Integer> integerList = new ArrayList<>();

        Random random = new Random();
        int expectedMax = random.nextInt();
        integerList.add(expectedMax);
        for (int i = 0; i < 9_999_999; i++) {
            int integer = random.nextInt();
            integerList.add(integer);
            if (integer > expectedMax) {
                expectedMax = integer;
            }
        }

        FindMaxMultythread<Integer> findMax = new FindMaxMultythread<>(4);
        Integer max = findMax.findMax(integerList);

        Assertions.assertEquals(expectedMax, max);
    }

    /**
     * Провекра случая когда размер коллекции меньше колличества потоков.
     */
    @Test
    public void testSmallColletion() throws ExecutionException, InterruptedException {
        List<Float> floatList = new ArrayList<>();

        Random random = new Random();
        floatList.add(random.nextFloat());
        floatList.add(random.nextFloat());
        Float expectedMax = Collections.max(floatList);

        FindMaxMultythread<Float> findMax = new FindMaxMultythread<>(3);
        Float max = findMax.findMax(floatList);

        Assertions.assertEquals(expectedMax, max);
    }

    /**
     * Провекрка случая когда коллекция состоит из одного элемента.
     */
    @Test
    public void testOneElemtCollection() throws ExecutionException, InterruptedException {
        List<Long> longList = new ArrayList<>();

        Random random = new Random();
        longList.add(random.nextLong());
        Long expectedMax = longList.get(0);

        FindMaxMultythread<Long> findMax = new FindMaxMultythread<>(2);
        Long max = findMax.findMax(longList);

        Assertions.assertEquals(expectedMax, max);
    }
}
