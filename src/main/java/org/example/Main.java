package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // the older version looked like this
        // TaskManager manager = new TaskManager();
        //TaskManager was self-contained, meaning:
        //It created its own ArrayList<Task> internally.
        //It loaded tasks itself from a hardcoded file (tasks.json) if the code had loadTasks() inside it.
        //Main didn’t provide any storage information — TaskManager did everything itself.

        // 1️⃣ Create a StorageManager first
        StorageManager storage = new StorageManager("tasks.json");
        // 2️⃣ Pass it to TaskManager
        // Now TaskManager doesn’t know how storage works,
        // it just knows: “I have a StorageManager, I can load/save tasks through it.”
        TaskManager manager = new TaskManager(storage);



        while (true) {
            System.out.println("Choose a number from the list below:");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Delete a task");
            System.out.println("4. Mark a task as completed");
            System.out.println("5. Edit a task");
            System.out.println("6. Exit");

            int choice = manager.getValidatedMenuChoice(scanner);

            switch (choice) {
                case 1 -> manager.addTask(scanner);
                case 2 -> manager.viewTask();
                case 3 -> manager.deleteTask(scanner);
                case 4 -> manager.markTaskDone(scanner);
                case 5 -> manager.editTask(scanner);
                case 6 -> {
                    manager.saveTasks();
                    System.out.println("Goodbye!");
                    return; // exit program
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }
}
