package ru.croc.java.school.lecture4.task2.Exceptions;

public class PutMapValueFailException extends Exception {

    public PutMapValueFailException (String errorMessage) {
        super(errorMessage);
    }
}
