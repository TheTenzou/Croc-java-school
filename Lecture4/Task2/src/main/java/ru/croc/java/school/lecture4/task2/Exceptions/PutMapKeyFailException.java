package ru.croc.java.school.lecture4.task2.Exceptions;

public class PutMapKeyFailException extends RuntimeException {

    public PutMapKeyFailException(String errorMessage) {
        super(errorMessage);
    }
}
