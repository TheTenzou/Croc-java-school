package ru.croc.java.school.lecture4.task2;

import org.junit.jupiter.api.Test;

public class HashMapTest {

    @Test
    public void testPut() {
        HashMap<Integer, String> hashMap = new HashMap<>();

        Integer integer = 1;
        String string = "string";

        hashMap.put(integer, string);

    }
}
