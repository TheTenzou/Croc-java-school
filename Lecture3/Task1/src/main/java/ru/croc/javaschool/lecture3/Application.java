package ru.croc.javaschool.lecture3;

import ru.croc.javaschool.lecture3.TaskManager.TaskManager;
import ru.croc.javaschool.lecture3.TaskManager.utils.ConsoleInPutOutPut;
import ru.croc.javaschool.lecture3.TaskManager.utils.ReadWriteObjects;

import java.util.Scanner;


public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TaskManager taskManager = new TaskManager(ReadWriteObjects.readTask(), ReadWriteObjects.readPerformers());

        ConsoleInPutOutPut.printGreeting();
        String command;
        do {
            command = scanner.nextLine();

            switch (command) {
                case "help":
                    ConsoleInPutOutPut.printHelp();
                    break;
                case "add task":
                    ConsoleInPutOutPut.addTask(taskManager);
                    break;
                case "add performer":
                    ConsoleInPutOutPut.addPerformer(taskManager);
                    break;
                case "show tasks":
                    ConsoleInPutOutPut.displayTasks(taskManager.getTasks());
                    System.out.println();
                    break;
                case "show performers":
                    ConsoleInPutOutPut.displayPerformers(taskManager.getPerformers());
                    System.out.println();
                    break;
                default:
                    System.out.println("Команда " + command + " не сушествует");
                    break;
            }
            System.out.print("> ");
        } while (!command.equals("exit"));
    }
}
