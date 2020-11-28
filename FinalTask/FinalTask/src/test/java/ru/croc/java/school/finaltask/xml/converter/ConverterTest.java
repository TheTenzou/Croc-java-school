package ru.croc.java.school.finaltask.xml.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.finaltask.xml.ResultStatistic;

import java.time.LocalDate;

public class ConverterTest {

    @Test
    public void testToXml() throws JsonProcessingException {
        ResultStatistic resultStatistic = new ResultStatistic(
                LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 10, 20),
                2.5
        );

        JaxbConverter jaxbConverter = new JaxbConverter();

        String xml = jaxbConverter.toXml(resultStatistic);

        String expectedXml = "<statistic>\r\n" +
                "   <start-date>10.10.2020</start-date>\r\n" +
                "   <end-date>20.10.2020</end-date>\r\n" +
                "   <ratio>2.5</ratio>\r\n" +
                "</statistic>";

        Assertions.assertEquals(expectedXml, xml);
    }
}
