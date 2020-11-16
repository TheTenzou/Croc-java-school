package ru.TheTenzou.croc.java.school.Lecture6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class FindMaxMultythreadTest {

    // TODO fix one element list
    @Test
    public void testFindMax() throws ExecutionException, InterruptedException {
        List<Integer> integerList = new ArrayList<>();

        Random random = new Random();
        Integer expectedMax = random.nextInt();
        integerList.add(expectedMax);
        for (int i = 0; i < 999_999; i++) {
            Integer integer = random.nextInt();
            integerList.add(integer);
            if (integer > expectedMax) {
                expectedMax =  integer;
            }
        }

        FindMaxMultythread<Integer> findMax = new FindMaxMultythread<>(4);
        Integer max = findMax.findMax(integerList);

        Assertions.assertEquals(expectedMax, max);
    }
}
