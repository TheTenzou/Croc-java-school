package ru.TheTenzou.croc.java.school.Lecture4.taks1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.TheTenzou.croc.java.school.Lecture4.task1.actor.ActorWithFilms;
import ru.TheTenzou.croc.java.school.Lecture4.task1.actor.Actors;
import ru.TheTenzou.croc.java.school.Lecture4.task1.actor.Film;
import ru.TheTenzou.croc.java.school.Lecture4.task1.film.Actor;
import ru.TheTenzou.croc.java.school.Lecture4.task1.film.FilmWithActors;
import ru.TheTenzou.croc.java.school.Lecture4.task1.JaxbConverter;
import ru.TheTenzou.croc.java.school.Lecture4.task1.film.Films;

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
    @DisplayName("Проверка преобразования фильма к xml")
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
    @DisplayName("Проверка преобразавания xml к фильму")
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
    @DisplayName("Проверка преобразования актера к xml")
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
    @DisplayName("Проверка преобразавания xml к актеру")
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
    @DisplayName("Проверка преобразования Фильмов к xml")
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
    @DisplayName("Проверка преобразования xml к фильмам")
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

    /**
     * Проверка преобразования актеров к xml.
     */
    @Test
    @DisplayName("Проверка преобразования актеров к xml")
    public void testConvertActorsToXml() throws Exception {
        JaxbConverter jaxbConverter = new JaxbConverter();

        List<Film> filmsFirstActor = Arrays.asList(
                new Film("Фильм 1", "Роль 1")
        );
        List<Film> filmsSecondActor = Arrays.asList(
                new Film("Фильм 1", "Роль 2"),
                new Film("Фильм 2", "Роль 5")
        );
        List<Film> filmsThirdActor = Arrays.asList(
                new Film("Фильм 1", "Роль 3"),
                new Film("Фильм 2", "Роль 6")
        );
        List<Film> filmsFrothActor = Arrays.asList(
                new Film("Фильм 2", "Роль 4")
        );

        Actors actors = new Actors(
                Arrays.asList(
                        new ActorWithFilms("Актер 1", 30, filmsFirstActor),
                        new ActorWithFilms("Актер 2", 23, filmsSecondActor),
                        new ActorWithFilms("Актер 3", 40, filmsThirdActor),
                        new ActorWithFilms("Актер 4", 70, filmsFrothActor)
                )
        );

        String xml = jaxbConverter.toXml(actors);

        Path path = Paths.get("src/main/resources", "Actors.xml");
        String expectedXml = Files.readString(path);

        Assertions.assertEquals(expectedXml, xml);
    }

    /**
     * Проверка преобразования xml к актерам.
     */
    @Test
    @DisplayName("Проверка преобразования xml к актерам")
    public void testConvertXmlToActors() throws Exception {
        JaxbConverter jaxbConverter = new JaxbConverter();

        Path path = Paths.get("src/main/resources", "Actors.xml");
        String xml = Files.readString(path);

        Actors actors = jaxbConverter.fromXml(xml, Actors.class);

        List<Film> filmsFirstActor = Arrays.asList(
                new Film("Фильм 1", "Роль 1")
        );
        List<Film> filmsSecondActor = Arrays.asList(
                new Film("Фильм 1", "Роль 2"),
                new Film("Фильм 2", "Роль 5")
        );
        List<Film> filmsThirdActor = Arrays.asList(
                new Film("Фильм 1", "Роль 3"),
                new Film("Фильм 2", "Роль 6")
        );
        List<Film> filmsFrothActor = Arrays.asList(
                new Film("Фильм 2", "Роль 4")
        );
        Actors expectedActors = new Actors(
                Arrays.asList(
                        new ActorWithFilms("Актер 1", 30, filmsFirstActor),
                        new ActorWithFilms("Актер 2", 23, filmsSecondActor),
                        new ActorWithFilms("Актер 3", 40, filmsThirdActor),
                        new ActorWithFilms("Актер 4", 70, filmsFrothActor)
                )
        );

        Assertions.assertEquals(expectedActors, actors);
    }
}
