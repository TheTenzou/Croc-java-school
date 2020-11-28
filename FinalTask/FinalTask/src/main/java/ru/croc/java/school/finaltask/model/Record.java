package ru.croc.java.school.finaltask.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Заспись ститиски по заболеванием короно вирусной инфекцией.
 */
public class Record {

    /**
     * Индетификатор записи.
     */
    private int id;

    /**
     * Название города.
     */
    private String city;

    /**
     * Дата вкоторую были произведены замеры.
     */
    private LocalDate date;

    /**
     * Колличество заболевших.
     */
    private int sickCount;

    /**
     * Колличество выздоровевших.
     */
    private int recoverCount;

    /**
     * Колличество погибших.
     */
    private int diedCount;

    /**
     * Конструктор.
     *
     * @param id           индетификатор записи
     * @param city         название города
     * @param date         дата проведения замера
     * @param sickCount    колличество заболеших
     * @param recoverCount колличество выздоровевших
     * @param diedCount    колличество погибших
     */
    public Record(int id, String city, LocalDate date, int sickCount, int recoverCount, int diedCount) {
        this.id = id;
        this.city = city;
        this.date = date;
        this.sickCount = sickCount;
        this.recoverCount = recoverCount;
        this.diedCount = diedCount;
    }

    /**
     * Конструктор.
     *
     * @param city         название города
     * @param date         дата проведения замера
     * @param sickCount    колличество заболеших
     * @param recoverCount колличество выздоровевших
     * @param diedCount    колличество погибших
     */
    public Record(String city, LocalDate date, int sickCount, int recoverCount, int diedCount) {
        this(-1, city, date, sickCount, recoverCount, diedCount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSickCount() {
        return sickCount;
    }

    public void setSickCount(int sickCount) {
        this.sickCount = sickCount;
    }

    public int getRecoverCount() {
        return recoverCount;
    }

    public void setRecoverCount(int recoverCount) {
        this.recoverCount = recoverCount;
    }

    public int getDiedCount() {
        return diedCount;
    }

    public void setDiedCount(int diedCount) {
        this.diedCount = diedCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id == record.id &&
                sickCount == record.sickCount &&
                recoverCount == record.recoverCount &&
                diedCount == record.diedCount &&
                Objects.equals(city, record.city) &&
                Objects.equals(date, record.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, date, sickCount, recoverCount, diedCount);
    }
}
