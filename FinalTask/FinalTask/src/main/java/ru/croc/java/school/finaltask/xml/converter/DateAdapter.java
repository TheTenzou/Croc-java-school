package ru.croc.java.school.finaltask.xml.converter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public LocalDate unmarshal(String s) {
        return LocalDate.parse(s, dateTimeFormatter);
    }

    @Override
    public String marshal(LocalDate localDate) {
        return localDate.format(dateTimeFormatter);
    }
}
