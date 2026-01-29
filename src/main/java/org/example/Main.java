package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("Choose a number from the list below:");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Delete a task");
            System.out.println("4. Mark a task as completed");
            System.out.println("5. Edit a task");
            System.out.println("6. Exit");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number! Try again.");
                continue; // go back to menu
            }

            switch (choice) {
                case 1 -> manager.addTask(scanner);
                case 2 -> manager.viewTask();
                case 3 -> manager.deleteTask(scanner);
                case 4 -> manager.markTaskDone(scanner);
                case 5 -> manager.editTask(scanner);
                case 6 -> {
                    System.out.println("Goodbye!");
                    return; // exit program
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }
}
