package ru.croc.javaschool.lecture3;

import ru.croc.javaschool.lecture3.TaskManager.Task.Performer;
import ru.croc.javaschool.lecture3.TaskManager.Task.Task;
import ru.croc.javaschool.lecture3.TaskManager.TaskManager;
import ru.croc.javaschool.lecture3.utils.ReadWriteObjects;

import java.util.Scanner;
import java.util.UUID;


public class Application {

    TaskManager taskManager;
    Scanner scanner = new Scanner(System.in);

    public Application() {
        taskManager = new TaskManager(ReadWriteObjects.readTask(), ReadWriteObjects.readPerformers());
    }

    /**
     * Приветсвуюшее сообшение.
     */
    private void printGreeting() {
        System.out.println("системы ведения задач");
        System.out.println("Введите команду:");
        System.out.print("> ");
    }

    /**
     * Вывод на экран списка команд.
     */
    private void printHelp() {
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
        System.out.println("  asign performer <performer-id> <task code> - ");
        System.out.println("  save - охраинть изменеия");
    }

    /**
     * Добавление задачи.
     */
    private void addTask() {
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
    private void addPerformer() {

        System.out.print("  Имя: ");
        String firstName = scanner.nextLine();

        System.out.print("  Фамилия: ");
        String secondName = scanner.nextLine();

        taskManager.addPerformer(new Performer(firstName, secondName));
    }

    /**
     * Вывод на экран списка задачь.
     */
    private void displayTasks() {
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
    private void displayPerformers() {
        for (Performer performer : taskManager.getPerformers()) {
            System.out.println();
            System.out.println("  Код:     " + performer.getId());
            System.out.println("  Имя:     " + performer.getFirstName());
            System.out.println("  Фамилия: " + performer.getLastName());
        }
    }

    /**
     * Из менить исполнителя.
     * @param command команда
     */
    private void asignePerforemer(String command) {
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
     * @param command команда
     */
    private void completeTask(String command) {
        UUID taskUUDI;
        try {
            taskUUDI = UUID.fromString(command.substring(14, 50));
        } catch (Exception e) {
            System.out.println("  Ошибка ввода кода задачи");
            return;
        }

        taskManager.getTask(taskUUDI).complete();

        System.out.println("  Запись была успешно изменена");
    }

    /**
     * Сохранение изменеий.
     */
    private void save() {
        ReadWriteObjects.writeObjects(taskManager.getTasks(), "tasks.out");
        ReadWriteObjects.writeObjects(taskManager.getPerformers(), "performers.out");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Application application = new Application();

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
            } else if (command.startsWith("asign performer")) {
                application.asignePerforemer(command);
            } else if (command.startsWith("complete task")) {
                application.completeTask(command);
            }
            else if (command.equals("save")) {
                application.save();
            }
            else if (command.equals("exit")) {
                System.out.println("  Завершение работы.");
            } else {
                System.out.println("  Команда " + command + " не сушествует");
            }
            System.out.print("> ");
        } while (!command.equals("exit"));
    }
}
