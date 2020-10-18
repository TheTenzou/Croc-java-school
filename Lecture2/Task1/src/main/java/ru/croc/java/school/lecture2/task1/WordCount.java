package ru.croc.java.school.lecture2.task1;

import java.util.HashMap;

/**
 * Подсчет количество слов тексте
 */
public abstract class WordCount {

    /**
     * Методо считающий количество слов тексте
     * @param text текст
     * @return hashmap где ключи это слов, а значения колчичество вхождений
     */
    public static HashMap<String, Integer> wordsCount(String text) {
        if (text.length() == 0) {
            return new HashMap<>();
        }

        HashMap<String, Integer> result = new HashMap<>();
        String[] words = text.strip().toLowerCase().replaceAll("[^a-zа-я]", " ").split("\\s+");
        for (String word : words) {
            result.put(word, result.getOrDefault(word, 0) + 1);
        }

        return result;
    }


}
