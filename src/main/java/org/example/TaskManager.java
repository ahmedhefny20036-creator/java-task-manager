package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.reflect.TypeToken;


public class TaskManager {
    private ArrayList<Task> tasks;
    // This is the in-memory list of tasks
    // It will store all tasks while the program is running

    private StorageManager storage;
    // Reference to StorageManager (our "assistant" for reading/writing tasks)
    // TaskManager doesn't know how storage works, it just delegates

    // Constructor → called when creating a new TaskManager object
    public TaskManager(StorageManager storage) {
        this.storage = storage;           // Save the reference so we can use it in other methods
        this.tasks = storage.loadTasks(); // Load tasks from storage at the start
        // If storage file exists → tasks contains previous tasks
        // If storage file doesn't exist → tasks will be a new empty list
    }

    public void saveTasks() {
        storage.saveTasks(tasks);
        // Pass the in-memory tasks list to StorageManager to save
        // saveTasks method just delegates saving to StorageManager
    }

    // Why loadTasks() isn’t explicitly needed anymore
    // We moved the actual loading logic to StorageManager, which knows how to read the JSON file.
    // Now, the TaskManager constructor already calls storage.loadTasks() when a new TaskManager is created:
    // This means that as soon as the TaskManager exists, tasks is already populated with whatever is in storage

    public void addTask(Scanner scanner) {
        System.out.println(" Enter a task name");
        String name = scanner.nextLine();

        Priority priority = null;

        while (priority == null) {
            System.out.println("Enter priority (HIGH / MEDIUM / LOW):");
            String input = scanner.nextLine().trim().toUpperCase();

            //try converting to enum: Priority.valueOf(input)
            //Converts string "HIGH" → enum Priority.HIGH
            //If input is invalid, throws IllegalArgumentException

            try {
                priority = Priority.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority! Must be HIGH, MEDIUM, or LOW. Try again.");
            }
        }
        Task newTask = new Task(name, priority);
        tasks.add(newTask);
        System.out.println("Task added!");
    }

    public void viewTask() {
        if (tasks.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Your tasks are:");
            displayTasks();
        }
    }

    public void deleteTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("The list is already empty");
            return;
        }
        System.out.println("Your tasks are:");
        displayTasks();

        System.out.println("Choose the number of the task you want to delete:");
        int taskNum = getValidTaskNumber(scanner);

        Task removedTask = tasks.remove(taskNum - 1);
        System.out.println("Task" + removedTask.getName() + "' deleted!");
    }

    public void markTaskDone(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("The list is empty, nothing to mark.");
            return;
        }
        System.out.println("Your tasks are:");
        displayTasks();

        System.out.println("Choose the number of the task to mark as done:");
        int taskNum = getValidTaskNumber(scanner);

        Task task = tasks.get(taskNum - 1);
        task.setCompleted(true);

        System.out.println("Task '" + task.getName() + "' marked as completed!");

    }

    public void editTask(Scanner scanner) {

        if (tasks.isEmpty()) {
            System.out.println("The list is empty, nothing to edit.");
            return;
        }
        System.out.println("Your tasks are:");
        displayTasks();

        System.out.println("Choose the number of the task you want to edit:");
        int taskNum = getValidTaskNumber(scanner);  // helper validates input
        Task task = tasks.get(taskNum - 1);

        System.out.println("Task selected: " + task);
        System.out.println("What do you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Priority");
        System.out.println("3. Done status");

        int choice = -1;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) break;
                else System.out.println("Number out of range! Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number! Try again.");
            }
        }
        switch (choice) {
            case 1: // Name
                System.out.println("Enter the new name:");
                String newName = scanner.nextLine();
                task.setName(newName);
                System.out.println("Name updated!");
                break;
            case 2: // Priority
                Priority newPriority = null;
                while (newPriority == null) {
                    System.out.println("Enter the new priority (HIGH / MEDIUM / LOW):");
                    String input = scanner.nextLine().trim().toUpperCase();
                    try {
                        newPriority = Priority.valueOf(input);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid priority! Must be HIGH, MEDIUM, or LOW. Try again.");
                    }
                }
                task.setPriority(newPriority);
                System.out.println("Priority updated!");
                break;

            case 3: // Done status
                System.out.println("Do you want to mark this task as done? (true/false):");
                boolean doneStatus = Boolean.parseBoolean(scanner.nextLine());
                task.setCompleted(doneStatus);
                System.out.println("Done status updated!");
                break;

            default:
                System.out.println("Invalid option! Try again.");
                break;

        }
    }


    // Helper methods used a lot
    private void displayTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + "." + t);
        }
    }

    private int getValidTaskNumber(Scanner scanner) {
        int num = -1;
        while (true) {
            try {
                num = Integer.parseInt(scanner.nextLine());
                if (num >= 1 && num <= tasks.size()) {
                    break;
                } else {
                    System.out.println("Number out of range! Try again.");
                }

            } catch (NumberFormatException e) {
                // Only runs if a NumberFormatException happens
                //e is just a variable name for the Exception object that Java creates automatically when the exception happens.
                System.out.println("Not a valid number. Please try again");
            }
        }
        return num;
    }

    public int getValidatedMenuChoice(Scanner scanner) {
        int choice = -1;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number! Try again.");
            }
        }
    }
}




