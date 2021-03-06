package ru.TheTenzou.croc.java.school.Lecture4.task1;

import ru.TheTenzou.croc.java.school.Lecture4.task1.actor.ActorWithFilms;
import ru.TheTenzou.croc.java.school.Lecture4.task1.actor.Actors;
import ru.TheTenzou.croc.java.school.Lecture4.task1.actor.Film;
import ru.TheTenzou.croc.java.school.Lecture4.task1.film.Actor;
import ru.TheTenzou.croc.java.school.Lecture4.task1.film.FilmWithActors;
import ru.TheTenzou.croc.java.school.Lecture4.task1.film.Films;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс предоставляюший возможность преобразование xml.
 */
public class ConvertXml {

    /**
     * Преобразование xml из списка фильмов с актерами к списку акторов с ролями.
     *
     * @param filmsXml исходная xml
     * @return преобразованная xml
     */
    public String FilmsToActors(String filmsXml) throws IOException {
        JaxbConverter jaxbConverter = new JaxbConverter();
        Films films = jaxbConverter.fromXml(filmsXml, Films.class);

        List<ActorWithFilms> actorsWithFilms = new ArrayList<>();

        for (FilmWithActors film : films.getFilms()) {
            addActors(actorsWithFilms, film);
        }

        Actors actors = new Actors(actorsWithFilms);

        return jaxbConverter.toXml(actors);
    }

    /**
     * Добаление всех актеров из вильмя в список актеров.
     *
     * @param actorsWithFilm список актеров
     * @param filmWithActors фильм
     */
    private void addActors(List<ActorWithFilms> actorsWithFilm, FilmWithActors filmWithActors) {
        for (Actor actor : filmWithActors.getActors()) {
            ActorWithFilms actorWithFilms = new ActorWithFilms(actor.getName(), actor.getAge());
            Film film = new Film(filmWithActors.getTitle(), actor.getRole());
            addRole(actorsWithFilm, actorWithFilms, film);
        }
    }

    /**
     * Добаление роли актеру если актера нет в списке то актер добаляется в список.
     *
     * @param actors список актеров
     * @param actor  актер
     * @param film   роль в фильме
     */
    private void addRole(List<ActorWithFilms> actors, ActorWithFilms actor, Film film) {
        boolean actorDontExitst = true;

        for (ActorWithFilms actorWithFilms : actors) {
            if (actorWithFilms.getName().equals(actor.getName())) {
                actorWithFilms.addFilm(film);
                actorDontExitst = false;
            }
        }
        if (actorDontExitst) {
            actor.addFilm(film);
            actors.add(actor);
        }
    }
}
