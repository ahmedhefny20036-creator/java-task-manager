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
    ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Scanner scanner) {
        System.out.println(" Enter a task name");
        String name = scanner.nextLine();

       Priority priority = null;

       while (priority == null){
           System.out.println("Enter priority (HIGH / MEDIUM / LOW):");
           String input = scanner.nextLine().trim().toUpperCase();

           //try converting to enum: Priority.valueOf(input)
           //Converts string "HIGH" → enum Priority.HIGH
           //If input is invalid, throws IllegalArgumentException

           try{
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
                while(newPriority == null) {
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
    public void saveTasks(){
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter("tasks.json");
            // FileWriter → Java class used to write TEXT to a file
            // writer → an object (variable) that represents an open connection to a file
            // "tasks.json" → the file path/name the writer is responsible for
            // If the file does not exist → Java creates it
            // If the file exists → Java opens it and overwrites its contents

            gson.toJson(tasks, writer);
            // use my translator gson and translate the java objects in my tasks arraylist into Json
            // save it in my FileWriter object called writer

            writer.close();
            //very important

        }catch (IOException e){
            //A general class for file-related error
            //Covers Missing file, Permission issues, Disk errors
            System.out.println("Could not save tasks.");
        }
    }

    public void loadTasks(){
        Gson gson = new Gson();
        try{
            FileReader reader = new FileReader("tasks.json");

            tasks = gson.fromJson(reader, new TypeToken<ArrayList<Task>>(){}.getType());
            // tasks → the ArrayList<Task> in memory that will store all loaded tasks
            // gson.fromJson(...) → converts JSON text from the reader into Java objects
            // reader → FileReader object pointing to "tasks.json", the source of JSON text
            // new TypeToken<ArrayList<Task>>(){}.getType() → tells Gson:
            //     "the JSON should become an ArrayList containing Task objects"
            //      without this, Gson wouldn't know the type of the items in the list
            // Result → tasks now contains all previously saved tasks

            reader.close();
        } catch (FileNotFoundException e){
            // Happens if tasks.json doesn’t exist (first run)
            tasks = new ArrayList<>(); // start fresh
            System.out.println("No previous tasks found, starting with empty list.");
        } catch (IOException e) {
            //A general class for file-related error
            // there can be multiple catches to a try
            System.out.println("Could not load tasks.");
        }
    }
}




