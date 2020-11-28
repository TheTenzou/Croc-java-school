package ru.croc.java.school.finaltask.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.finaltask.xml.converter.JaxbConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class ConverterTest {

    /**
     * Проверка преобразование к xml.
     */
    @Test
    @DisplayName("Проверка преобразование к xml")
    public void testToXml() throws IOException {
        RatioStatistic resultStatistic = new RatioStatistic(
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

    /**
     * Проверка преобразование из xml.
     */
    @Test
    @DisplayName("Проверка преобразование из xml")
    public void testFromXml() throws IOException {
        JaxbConverter jaxbConverter = new JaxbConverter();

        Path path = Paths.get("src/test/resources", "test.xml");
        String xml = Files.readString(path);

        RatioStatistic resultStatistic = jaxbConverter.fromXml(xml, RatioStatistic.class);

        RatioStatistic expedtedResult = new RatioStatistic(
                LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 10, 20),
                2.5
        );

        Assertions.assertEquals(expedtedResult, resultStatistic);
    }
}
