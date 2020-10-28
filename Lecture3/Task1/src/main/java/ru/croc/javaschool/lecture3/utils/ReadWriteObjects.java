package ru.croc.javaschool.lecture3.utils;

import ru.croc.javaschool.lecture3.TaskManager.Task.Performer;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Передоставляет функции для записи с чидаывния задачь и исполнителей в файл.
 */
public class ReadWriteObjects {

    /**
     * Считывание задачь из файла.
     *
     * @return список задачь
     */
    public static ArrayList<Task> readTask() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("tasks.out"))) {

            ArrayList<Task> tasks = new ArrayList<>();

            try {
                for (; ; ) {
                    Task task = (Task) objectInputStream.readObject();
                    tasks.add(task);
                }
            } catch (EOFException e) {
                return tasks;
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Считывание исполнителей из файла.
     *
     * @return список исполнителей
     */
    public static ArrayList<Performer> readPerformers() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("performers.out"))) {

            ArrayList<Performer> tasks = new ArrayList<>();

            try {
                for (; ; ) {
                    Performer performer = (Performer) objectInputStream.readObject();
                    tasks.add(performer);
                }
            } catch (EOFException e) {
                return tasks;
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
