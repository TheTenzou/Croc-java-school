package ru.croc.java.school.lecture4.taks1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.lecture4.task1.ConvertXml;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CoverterXmlTest {

    /**
     * Проверка преобразовая xml.
     */
    @Test
    @DisplayName("Проверка преобразовая xml")
    public void testFilmsToActors() throws Exception {

        Path filmsPath = Paths.get("src/main/resources", "Films.xml");
        String filmsXml = Files.readString(filmsPath);

        ConvertXml convertXml = new ConvertXml();
        String actorsXml = convertXml.FilmsToActors(filmsXml);

        Path actorsPath = Paths.get("src/main/resources", "ACtors.xml");
        String expectedActorsXml = Files.readString(actorsPath);

        Assertions.assertEquals(expectedActorsXml, actorsXml);
    }
}
