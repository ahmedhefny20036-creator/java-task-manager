package org.example;

import java.util.Scanner;
import java.util.ArrayList;


public class TaskManager {
    ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Scanner scanner) {
        System.out.println(" Enter a task name");
        String name = scanner.nextLine();

        System.out.println("Enter priority (HIGH / MEDIUM / LOW):");
        String priority = getValidPriority(scanner);

        Task newTask = new Task(name, priority);
        tasks.add(newTask);
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
        System.out.println("Task" + removedTask.getName() + "deleted");
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
                System.out.println("Enter the new priority (HIGH / MEDIUM / LOW):");
                String newPriority = getValidPriority(scanner);  // uses helper method
                task.setPriority(newPriority);
                System.out.println("Priority updated!");
                break;
            case 3: // Done status
                System.out.println("Do you want to mark this task as done? (true/false):");
                boolean doneStatus = Boolean.parseBoolean(scanner.nextLine());
                task.setCompleted(doneStatus);
                System.out.println("Done status updated!");
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

    private String getValidPriority(Scanner scanner) {
        String priority;
        while (true) {
            priority = scanner.nextLine().trim();
            String p = priority.toUpperCase();
            if (p.equals("HIGH") || p.equals("MEDIUM") || p.equals("LOW")) {
                break;
            } else {
                System.out.println("Invalid priority! Must be High, Medium, or Low. Try again.");
            }

        }
        return priority;
    }
}




