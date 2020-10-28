package ru.croc.javaschool.lecture3;

import ru.croc.javaschool.lecture3.TaskManager.TaskManager;
import ru.croc.javaschool.lecture3.utils.ConsoleInPutOutPut;
import ru.croc.javaschool.lecture3.utils.ReadWriteObjects;

import java.util.Scanner;


public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TaskManager taskManager = new TaskManager(ReadWriteObjects.readTask(), ReadWriteObjects.readPerformers());

        ConsoleInPutOutPut.printGreeting();
        String command;
        do {
            command = scanner.nextLine();

            if (command.equals("help")) {
                ConsoleInPutOutPut.printHelp();
            }
            else if (command.equals("add task")) {
                ConsoleInPutOutPut.addTask(taskManager);
            }
            else if (command.equals("add performer")) {
                ConsoleInPutOutPut.addPerformer(taskManager);
            }
            else if (command.equals("show tasks")) {
                ConsoleInPutOutPut.displayTasks(taskManager.getTasks());
                System.out.println();
            }
            else if (command.equals("show performers")) {
                ConsoleInPutOutPut.displayPerformers(taskManager.getPerformers());
                System.out.println();
            }
            else if (command.startsWith("asign performer")) {
                String performerId;
                String taskCode;
                try {
                    performerId = command.substring(16, 52);
                    taskCode = command.substring(53, 89);
                    ConsoleInPutOutPut.asignePerforemer(taskManager, performerId, taskCode);
                } catch (Exception e) {
                    System.out.println("Ошибка ввода кода задачи или исполнителя");
                }
//                ConsoleInPutOutPut.asignePerforemer(taskManager, );
            }
            else if (command.equals("exti")) {
                System.out.println("Завершение работы.");
            }
            else {
                System.out.println("Команда " + command + " не сушествует");
            }
            System.out.print("> ");
        } while (!command.equals("exit"));
    }
}
