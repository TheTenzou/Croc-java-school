package ru.croc.java.school.lecture2.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static ru.croc.java.school.lecture2.task1.WordCount.wordsCount;

public class  WordCountTest {

    @Test
    public void wordCountTest() {
        Map<String, Integer> words = wordsCount("");
        Assertions.assertEquals(new HashMap<String, Integer>(), words);

        words = wordsCount("слово");
        Map<String, Integer> result = new HashMap<>();
        result.put("слово", 1);

        Assertions.assertEquals(result, words);

        words = wordsCount("один два два");
        result = new HashMap<>();
        result.put("один", 1);
        result.put("два", 2);

        Assertions.assertEquals(result, words);

        words = wordsCount("ЗагЛавные буквы загЛавные БУКВЫ");
        result = new HashMap<>();
        result.put("заглавные", 2);
        result.put("буквы", 2);

        Assertions.assertEquals(result, words);

        words = wordsCount(" очень            очень          очень     много         пробелов  ");
        result = new HashMap<>();
        result.put("очень", 3);
        result.put("много", 1);
        result.put("пробелов", 1);

        Assertions.assertEquals(result, words);

        words = wordsCount(" очень .!очень? ,, очень .\"много \" занков:препенания");
        result = new HashMap<>();
        result.put("очень", 3);
        result.put("много", 1);
        result.put("занков", 1);
        result.put("препенания", 1);

        Assertions.assertEquals(result, words);
    }
}
