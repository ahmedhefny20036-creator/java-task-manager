package org.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StorageManager {
    private final String filePath; // where tasks will be saved

    public StorageManager(String filePath) {
        this.filePath = filePath;
    } // constructor
    public void saveTasks(ArrayList<Task> tasks) {
        Gson gson = new Gson(); // Gson will convert our Java objects → JSON text

        // try-with-resources automatically closes the writer when done
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(tasks, writer); // Convert the tasks list → JSON and save it to file
            // No need to call writer.close() manually — Java does it automatically because we are using try with resources
        } catch (IOException e) {
            // Happens if there is a problem with writing the file
            // e.g., no permission, disk full, or invalid path
            System.out.println("Could not save tasks.");
        }
    }

    public ArrayList<Task> loadTasks() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, new TypeToken<ArrayList<Task>>(){}.getType());
        } catch (FileNotFoundException e) {
            System.out.println("No previous tasks found, starting with empty list.");
            return new ArrayList<>(); // empty list if file doesn't exist
        } catch (IOException e) {
            System.out.println("Could not load tasks.");
            return new ArrayList<>(); // empty list if some error happens
        }
    }
}
