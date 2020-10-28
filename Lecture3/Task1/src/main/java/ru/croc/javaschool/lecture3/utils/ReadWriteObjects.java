package ru.croc.javaschool.lecture3.utils;

import ru.croc.javaschool.lecture3.TaskManager.Task.Performer;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;

import java.io.*;
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

    /**
     * Сохранение объекта в файл.
     *
     * @param object   объект
     * @param fileName название файла
     */
    public static void writeObject(Object object, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(object);
            System.out.println("Запись сохранена");
        } catch (Exception e) {
            System.out.println("Не удалось сохоанить запись");
        }
    }
}
