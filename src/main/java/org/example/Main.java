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
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("enter a task");
                String name = scanner.nextLine();

                System.out.println("enter priority (high/medium/low)");
                String priority = scanner.nextLine();

                Task newTask = new Task(name, priority);
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
                } else {
                    System.out.println("your tasks are");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        System.out.println((i + 1) + "." + t.name + " [" + t.priority + "]");
                    }
                    System.out.println("choose the number you want to delete");

                    int choice2 = scanner.nextInt();
                    scanner.nextLine();
                    if (choice2 >= 1 && choice2 <= tasks.size()) {
                        tasks.remove(choice2 - 1);
                        System.out.println("task deleted");
                    } else {
                        System.out.println("invalid task number");
                    }
                }
            } else if (choice == 4) {
                System.out.println("goodbye");
                break;
            } else {
                System.out.println("invalid option");
            }

        }
    }
}


