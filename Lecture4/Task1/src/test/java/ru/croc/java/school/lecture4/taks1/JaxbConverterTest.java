package ru.croc.java.school.lecture4.taks1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.school.lecture4.task1.actor.ActorWithFilms;
import ru.croc.java.school.lecture4.task1.actor.Film;
import ru.croc.java.school.lecture4.task1.film.Actor;
import ru.croc.java.school.lecture4.task1.film.FilmWithActors;
import ru.croc.java.school.lecture4.task1.JaxbConverter;
import ru.croc.java.school.lecture4.task1.film.Films;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class JaxbConverterTest {

    /**
     * Проверка преобразования фильма к xml.
     */
    @Test
    public void testCoverterFilmsToXml() throws Exception {
        JaxbConverter jaxbConverter = new JaxbConverter();
        List<Actor> actors = Arrays.asList(
                new Actor("Актер 1", 30, "Роль 1"),
                new Actor("Актер 2", 23, "Роль 2")
        );
        FilmWithActors film = new FilmWithActors("Назване", "Описание", actors);

        String xml = jaxbConverter.toXml(film);

        Path path = Paths.get("src/main/resources", "FilmTest.xml");
        String expectedXml = Files.readString(path);

        Assertions.assertEquals(expectedXml, xml);
    }

    /**
     * Проверка преобразавания xml к фильму.
     */
    @Test
    public void testConverterXmlToFilm() throws Exception {
        JaxbConverter jaxbConverter = new JaxbConverter();
        Path path = Paths.get("src/main/resources", "FilmTest.xml");
        String xml = Files.readString(path);

        FilmWithActors film = jaxbConverter.fromXml(xml, FilmWithActors.class);

        List<Actor> actors = Arrays.asList(
                new Actor("Актер 1", 30, "Роль 1"),
                new Actor("Актер 2", 23, "Роль 2")
        );
        FilmWithActors expectedFilm = new FilmWithActors("Назване", "Описание", actors);

        Assertions.assertEquals(expectedFilm, film);
    }

    /**
     * Проверка преобразования актера к xml.
     */
    @Test
    public void testConvertActorToXml() throws Exception {
        JaxbConverter jaxbConverter = new JaxbConverter();
        List<Film> actors = Arrays.asList(
                new Film("Фильм 1", "Роль 1"),
                new Film("Фильм 2", "Роль 2")
        );
        ActorWithFilms actor = new ActorWithFilms("Имя", 27, actors);

        String xml = jaxbConverter.toXml(actor);
        Path path = Paths.get("src/main/resources", "ActorTest.xml");
        String expectedXml = Files.readString(path);

        Assertions.assertEquals(expectedXml, xml);

    }

    /**
     * Проверка преобразавания xml к актеру.
     */
    @Test
    public void testConverterXmlToActor() throws Exception {
        JaxbConverter jaxbConverter = new JaxbConverter();
        Path path = Paths.get("src/main/resources", "ActorTest.xml");
        String xml = Files.readString(path);

        ActorWithFilms actor = jaxbConverter.fromXml(xml, ActorWithFilms.class);

        List<Film> actors = Arrays.asList(
                new Film("Фильм 1", "Роль 1"),
                new Film("Фильм 2", "Роль 2")
        );
        ActorWithFilms expectedActor = new ActorWithFilms("Имя", 27, actors);

        Assertions.assertEquals(expectedActor, actor);
    }

    /**
     * Проверка преобразования Фильмов к xml.
     */
    @Test
    public void testConvertFilmsToXml() throws Exception {
        JaxbConverter jaxbConverter = new JaxbConverter();
        List<Actor> actorsFirstFilm = Arrays.asList(
                new Actor("Актер 1", 30, "Роль 1"),
                new Actor("Актер 2", 23, "Роль 2"),
                new Actor("Актер 3", 40, "Роль 3")
        );
        List<Actor> actorsSecondfilm = Arrays.asList(
                new Actor("Актер 4", 70, "Роль 4"),
                new Actor("Актер 2", 23, "Роль 5"),
                new Actor("Актер 3", 40, "Роль 6")
        );
        Films films = new Films(Arrays.asList(
                new FilmWithActors("Фильм 1", "Описание 1", actorsFirstFilm),
                new FilmWithActors("Фильм 2", "Описание 2", actorsSecondfilm))
        );

        String xml = jaxbConverter.toXml(films);

        Path path = Paths.get("src/main/resources", "Films.xml");
        String expectedXml = Files.readString(path);

        Assertions.assertEquals(expectedXml, xml);
    }

    /**
     * Проверка преобразования xml к фильмам.
     */
    @Test
    public void testConvertXmlToFilms() throws Exception {
        JaxbConverter jaxbConverter = new JaxbConverter();

        Path path = Paths.get("src/main/resources", "Films.xml");
        String xml = Files.readString(path);
        Films films = jaxbConverter.fromXml(xml, Films.class);

        List<Actor> actorsFirstFilm = Arrays.asList(
                new Actor("Актер 1", 30, "Роль 1"),
                new Actor("Актер 2", 23, "Роль 2"),
                new Actor("Актер 3", 40, "Роль 3")
        );
        List<Actor> actorsSecondfilm = Arrays.asList(
                new Actor("Актер 4", 70, "Роль 4"),
                new Actor("Актер 2", 23, "Роль 5"),
                new Actor("Актер 3", 40, "Роль 6")
        );
        Films expectedFilms = new Films(Arrays.asList(
                new FilmWithActors("Фильм 1", "Описание 1", actorsFirstFilm),
                new FilmWithActors("Фильм 2", "Описание 2", actorsSecondfilm))
        );

        Assertions.assertEquals(expectedFilms, films);
    }
}
