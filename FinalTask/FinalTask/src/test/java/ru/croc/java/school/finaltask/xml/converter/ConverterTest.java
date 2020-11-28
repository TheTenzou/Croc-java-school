package ru.croc.java.school.finaltask.xml.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.finaltask.xml.ResultStatistic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class ConverterTest {

    @Test
    public void testToXml() throws IOException {
        ResultStatistic resultStatistic = new ResultStatistic(
                LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 10, 20),
                2.5
        );

        JaxbConverter jaxbConverter = new JaxbConverter();

        String xml = jaxbConverter.toXml(resultStatistic);

        Path path = Paths.get("src/test/resources", "test.xml");
        String expectedXml = Files.readString(path);

        Assertions.assertEquals(expectedXml, xml);
    }
}
