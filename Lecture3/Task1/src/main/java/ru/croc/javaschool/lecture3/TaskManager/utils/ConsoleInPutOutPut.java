package ru.croc.javaschool.lecture3.TaskManager.utils;

import ru.croc.javaschool.lecture3.TaskManager.Task.Performer;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;
import ru.croc.javaschool.lecture3.TaskManager.TaskManager;

import java.util.Collection;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleInPutOutPut {

    /**
     * Вывод на экран списка команд.
     */
    public static void printHelp() {
        System.out.println("  help - список соманд");
        System.out.println("  exit - выход");
        System.out.println("  add task - добавить задачу");
        System.out.println("  add performer - добавть исполнителя");
        System.out.println("  show tasks - отоюразть список задачь");
        System.out.println("  show performers - вывести список исполнителей");
        System.out.println("  show task <code> - вывести задачу с заданым кодом");//sdf
        System.out.println("  show performer <id> - вывести исполнителя c заданым id");//asdf
        System.out.println("  show completed tasks - ");//asdf
        System.out.println("  show uncompleted tasks - ");//adf
        System.out.println("  delete task <code> - удалить задачу с заданым кодом");
        System.out.println("  delete performer <id> - удалить исполнителя c заданым id");
        System.out.println("  complete task <code> - изменить стаус задачи на завершить");
        System.out.println("  set performer <task code> <performer-id> - ");
    }

    /**
     * Добавление задачи.
     *
     * @param taskManager Система ведеия задачь
     */
    public static void addTask(TaskManager taskManager) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("  Назвние: ");
        String name = scanner.nextLine();

        System.out.print("  Описание: ");
        String description = scanner.nextLine();

        System.out.print("  Исполнитель: ");
        String id = scanner.nextLine();

        try {
            Performer performer = taskManager.getPerformer(UUID.fromString(id));
            taskManager.addTask(new Task(name, description, performer));
            System.out.println("  Задача добавлена");
        } catch (Exception e) {
            System.out.println("  Исполнитель не найден.");
            System.out.println("  Добавть задаче без исолнителя? (д/н)");
            String answer = scanner.nextLine().toLowerCase();

            if (answer.startsWith("l") || answer.startsWith("д")) {
                taskManager.addTask(new Task(name, description, null));
                System.out.println("  Задача добавлена");
            } else {
                System.out.println("  Задача не добавленна");
            }
        }
    }

    /**
     * Добалвение задачи.
     *
     * @param taskManager Система ведеия задачь
     */
    public static void addPerformer(TaskManager taskManager) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("  Имя: ");
        String firstName = scanner.nextLine();

        System.out.print("  Фамилия: ");
        String secondName = scanner.nextLine();

        taskManager.addPerformer(new Performer(firstName, secondName));
        System.out.println("  Исполнитель добавлен");
    }

    /**
     * Вывод на экран списка задачь.
     *
     * @param tasks задачи
     */
    public static void displayTasks(Collection<Task> tasks) {
        for (Task task : tasks) {
            System.out.println();
            System.out.println("  Код:        " + task.getCode());
            System.out.println("  Назване:    " + task.getName());
            Performer performer = task.getPerformer();

            if (performer != null) {
                System.out.println("  Имя:       " + performer.getFirstName());
                System.out.println("  Фамилия:   " + performer.getFirstName());
            } else {
                System.out.println("  Исполнитель: не назначен");
            }

            if (task.getState()) {
                System.out.println("  Состояние:  Завершен");
            } else {
                System.out.println("  Состояние:  Не завершен");
            }

            System.out.println("  Описание:   " + task.getDescription());
        }
    }

    /**
     * Вывод на экран списка исполнителей.
     *
     * @param performers список исполнителей
     */
    public static void displayPerformers(Collection<Performer> performers) {
        for (Performer performer : performers) {
            System.out.println();
            System.out.println("  Код:     " + performer.getId());
            System.out.println("  Имя:     " + performer.getFirstName());
            System.out.println("  Фамилия: " + performer.getLastName());
        }
    }
}
