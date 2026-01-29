package org.example;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.println("choose a number from the list below");

            System.out.println("1. Add task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete a task");
            System.out.println("4. Mark a task as completed");
            System.out.println("5. Edit a task");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("enter a task");
                String name = scanner.nextLine();

                Task newTask = new Task(name, ""); // temporary priority

                while (newTask.getPriority() == null || newTask.getPriority().isBlank()) {
                    System.out.println("enter priority (high / medium / low)");
                    String priority = scanner.nextLine();
                    newTask.setPriority(priority);
                }

                tasks.add(newTask);
                System.out.println("task added");
            } else if (choice == 2) {
                if (tasks.isEmpty()) {
                    System.out.println("the list is empty");
                } else {
                    System.out.println("your tasks are");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        System.out.println((i + 1) + "." + t);
                    }
                }
            } else if (choice == 3) {
                if (tasks.isEmpty()) {
                    System.out.println("the list is already empty");
                } int choice2 = -1;

                while (true) {
                    System.out.println("Choose the number of the task you want to delete:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        System.out.println((i + 1) + "." + t);
                    }

                    try {
                        // Read the input as a string, then convert to int
                        choice2 = Integer.parseInt(scanner.nextLine());

                        if (choice2 >= 1 && choice2 <= tasks.size()) {
                            break; // valid → exit the loop
                        } else {
                            System.out.println("Number out of range! Try again.");
                        }

                    } catch (NumberFormatException e) {
                        // If the input cannot be converted to an integer
                        System.out.println("Not a valid number! Try again.");
                    }
                }
                tasks.remove(choice2 - 1);
                System.out.println("Task deleted!");

            } else if (choice == 4) {
                if (tasks.isEmpty()) {
                    System.out.println("the list is already empty");
                } else {
                    System.out.println("your tasks are");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        System.out.println((i + 1) + "." + t);
                    }

                    System.out.println("choose the number you want to mark as done");
                    int choice3 = scanner.nextInt();
                    scanner.nextLine();

                    if (choice3 >= 1 && choice3 <= tasks.size()) {
                        tasks.get(choice3 - 1).setCompleted(true);  // mark the task as done
                        System.out.println("Task marked as completed ");
                    } else {
                        System.out.println("Invalid task number");
                    }
                }
            } else if (choice == 5) {
                if (tasks.isEmpty()) {
                    System.out.println("The list is empty, nothing to edit.");
                } else {
                    System.out.println("your tasks are");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        System.out.println((i + 1) + "." + t);
                    }
                }
                System.out.println("please enter a valid number");
            }else if (choice == 6) {
                System.out.println("goodbye");
                break;
            } else {
                System.out.println("invalid option");
            }

        }
    }
}


