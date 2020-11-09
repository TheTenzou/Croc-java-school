package ru.croc.javaschool.lecture3;

import ru.croc.javaschool.lecture3.TaskManager.Task.Performer;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;
import ru.croc.javaschool.lecture3.TaskManager.TaskManager;
import ru.croc.javaschool.lecture3.utils.ReadWriteObjects;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;


public class Application {

    /**
     * Менеджер задач.
     */
    private TaskManager taskManager;

    /**
     * Сканер.
     */
    private Scanner scanner;

    /**
     * Имя файла с задачим.
     */
    private String taskFileName;

    /**
     * Имя файла с исполнителями.
     */
    private String performersFileName;

    /**
     * Конструктор.
     *
     * @param taskFileName       название файла с задачами
     * @param performersFileName название файла с исполнителями
     * @param scanner            сканер для считывания данных
     */
    public Application(String taskFileName, String performersFileName, Scanner scanner) {
        this.scanner = scanner;
        this.taskFileName = taskFileName;
        this.performersFileName = performersFileName;
        taskManager = new TaskManager(ReadWriteObjects.readTask(taskFileName), ReadWriteObjects.readPerformers(performersFileName));
    }

    /**
     * Приветсвуюшее сообшение.
     */
    public void printGreeting() {
        System.out.println("  Cистемы ведения задач");
        System.out.println("  Введите команду:");
        System.out.print("> ");
    }

    /**
     * Вывод на экран списка команд.
     */
    public void printHelp() {
        System.out.println("  help - список соманд");
        System.out.println("  exit - выход");
        System.out.println("  add task - добавить задачу");
        System.out.println("  add performer - добавть исполнителя");
        System.out.println("  show tasks - отоюразть список задачь");
        System.out.println("  show performers - вывести список исполнителей");
        System.out.println("  delete task <code> - удалить задачу с заданым кодом");
        System.out.println("  delete performer <id> - удалить исполнителя c заданым id");
        System.out.println("  complete task <code> - изменить стаус задачи на завершить");
        System.out.println("  asign performer <performer-id> <task code> - назначить исполнитля задаче");
        System.out.println("  save - охраинть изменеия");
    }

    /**
     * Добавление задачи.
     */
    public void addTask() {
        System.out.print("  Назвние: ");
        String name = scanner.nextLine();

        System.out.print("  Описание: ");
        String description = scanner.nextLine();

        System.out.print("  Исполнитель: ");
        String id = scanner.nextLine();

        try {
            Performer performer = taskManager.getPerformer(UUID.fromString(id));
            taskManager.addTask(new Task(name, description, performer));
        } catch (Exception e) {
            System.out.println("  Исполнитель не найден.");
            System.out.println("  Добавть задаче без исолнителя? (д/н)");
            String answer = scanner.nextLine().toLowerCase();

            if (answer.startsWith("l") || answer.startsWith("д")) {
                taskManager.addTask(new Task(name, description, null));
            } else {
                System.out.println("  Задача не добавленна");
            }
        }
    }

    /**
     * Добалвение задачи.
     */
    public void addPerformer() {

        System.out.print("  Имя: ");
        String firstName = scanner.nextLine();

        System.out.print("  Фамилия: ");
        String secondName = scanner.nextLine();

        taskManager.addPerformer(new Performer(firstName, secondName));
    }

    /**
     * Вывод на экран списка задачь.
     */
    public void displayTasks() {
        for (Task task : taskManager.getTasks()) {
            System.out.println();
            System.out.println("  Код:        " + task.getCode());
            System.out.println("  Назване:    " + task.getName());
            Performer performer = task.getPerformer();

            if (performer != null) {
                System.out.println("  Имя:        " + performer.getFirstName());
                System.out.println("  Фамилия:    " + performer.getFirstName());
            } else {
                System.out.println("  Исполнитель: не назначен");
            }

            if (task.getState()) {
                System.out.println("  Состояние:   Завершен");
            } else {
                System.out.println("  Состояние:   Не завершен");
            }

            System.out.println("  Описание:    " + task.getDescription());
        }
    }

    /**
     * Вывод на экран списка исполнителей.
     */
    public void displayPerformers() {
        for (Performer performer : taskManager.getPerformers()) {
            System.out.println();
            System.out.println("  Код:     " + performer.getId());
            System.out.println("  Имя:     " + performer.getFirstName());
            System.out.println("  Фамилия: " + performer.getLastName());
        }
    }

    /**
     * Удаление задачи.
     *
     * @param command команда
     */
    public void deleteTask(String command) {
        UUID taskCode;
        try {
            taskCode = UUID.fromString(command.substring(12, 48));
            taskManager.removeTask(taskCode);
            System.out.println("  Задача была удалена");
        } catch (Exception e) {
            System.out.println("  Не удалось удалить задачу");
        }
    }

    /**
     * Удаление исполнителя.
     *
     * @param command команда
     */
    public void deletePerformer(String command) {
        UUID performerId;
        try {
            performerId = UUID.fromString(command.substring(17, 53));
            taskManager.removePerformer(performerId);
            System.out.println("  Исполнитель был удален");
        } catch (Exception e) {
            System.out.println("  Не удалось удалить исполнителя");
        }
    }

    /**
     * Изменить исполнителя.
     *
     * @param command команда
     */
    public void asignePerforemer(String command) {
        UUID performerUUID;
        UUID taskUUID;

        try {
            performerUUID = UUID.fromString(command.substring(16, 52));
        } catch (Exception e) {
            System.out.println("  Ошибка в индетификаторе исполнителя");
            return;
        }

        try {
            taskUUID = UUID.fromString(command.substring(53, 89));
        } catch (Exception e) {
            System.out.println("  Ошибка в коде задаачи");
            return;
        }

        Task task = taskManager.getTask(taskUUID);
        Performer performer = taskManager.getPerformer(performerUUID);
        task.setPerformer(performer);

        System.out.println("  Запись была успешно изменена");
    }

    /**
     * Заершить задачу.
     *
     * @param command команда
     */
    public void completeTask(String command) {
        UUID taskUUDI;
        try {
            taskUUDI = UUID.fromString(command.substring(14, 50));
            taskManager.getTask(taskUUDI).complete();
            System.out.println("  Запись была успешно изменена");
        } catch (Exception e) {
            System.out.println("  Ошибка ввода кода задачи");
        }
    }

    /**
     * Сохранение изменеий.
     */
    public void save() {
        try {
            ReadWriteObjects.writeObjects(taskManager.getTasks(), taskFileName);
            System.out.println("  Задачи сохранены");
        } catch (IOException e) {
            System.out.println("  Не удалось сохранить задачи");
        }
        try {
            ReadWriteObjects.writeObjects(taskManager.getPerformers(), performersFileName);
            System.out.println("  Исполнители сохранены");
        } catch (IOException e) {
            System.out.println("  Не удалось сохранить исполнителей");
        }
    }

    public static void main(String[] args) {

        String taskFilename = "tasks.out";
        String performersFileName = "performers.out";

        if (args.length == 2) {
            taskFilename = args[0];
            performersFileName = args[1];
        }

        Scanner scanner = new Scanner(System.in);

        Application application = new Application(taskFilename, performersFileName, scanner);

        application.printGreeting();
        String command;
        do {
            command = scanner.nextLine();

            if (command.equals("help")) {
                application.printHelp();
            } else if (command.equals("add task")) {
                application.addTask();
            } else if (command.equals("add performer")) {
                application.addPerformer();
            } else if (command.equals("show tasks")) {
                application.displayTasks();
                System.out.println();
            } else if (command.equals("show performers")) {
                application.displayPerformers();
                System.out.println();
            } else if (command.startsWith("delete task")) {
                application.deleteTask(command);
            } else if (command.startsWith("delete performer")) {
                application.deletePerformer(command);
            } else if (command.startsWith("asign performer")) {
                application.asignePerforemer(command);
            } else if (command.startsWith("complete task")) {
                application.completeTask(command);
            } else if (command.equals("save")) {
                application.save();
            } else if (command.equals("exit")) {
                System.out.println("  Завершение работы.");
            } else {
                System.out.println("  Команда " + command + " не сушествует");
            }
            System.out.print("> ");
        } while (!command.equals("exit"));
    }
}
