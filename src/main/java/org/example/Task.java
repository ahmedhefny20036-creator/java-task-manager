package org.example;

public class Task {
    String name;
    String priority;
    public Task(String name, String priority){
        this.name = name;
        this.priority = priority;
    }
    // @Override is optional but recommended —
    // it tells Java: “I’m intentionally overriding the default Object toString() method”.
    @Override
    public String toString (){
        return name + "[" + priority +"]";
    }
}
