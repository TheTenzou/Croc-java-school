package ru.croc.java.school.finaltask.xml.converter.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * Формат даты.
     */
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Метод преобразуюший строку в LocalDate.
     *
     * @param s строковое предсталение даты
     * @return дата
     */
    @Override
    public LocalDate unmarshal(String s) {
        return LocalDate.parse(s, dateTimeFormatter);
    }

    /**
     * Метод преобразуюший LocalDate в стороку.
     *
     * @param localDate дата
     * @return строковое предсталение даты
     */
    @Override
    public String marshal(LocalDate localDate) {
        return localDate.format(dateTimeFormatter);
    }
}
