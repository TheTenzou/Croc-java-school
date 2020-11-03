package ru.croc.java.school.lecture4.task2.books;

import ru.croc.java.school.lecture4.task2.Annotations.MapValueFail;

@MapValueFail
public class MarkedBook extends Book {
    public MarkedBook(String name, String author, String genre) {
        super(name, author, genre);
    }
}
