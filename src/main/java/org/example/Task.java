package org.example;

public class Task {
   private String name;
   private Priority priority;
   private boolean completed;

    public Task(String name, Priority priority){
        this.name = name;
        this.priority = priority;
    }

    public String getName(){
        return name;
    }
    public Priority getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    // @Override is optional but recommended —
    // it tells Java: “I’m intentionally overriding the default Object toString() method”.
    @Override
    public String toString() {
        // This part: (completed ? " [DONE]" : "") is called a ternary operator.
              return name + " [" + priority + "]" + (completed ? " [DONE]" : "");
    }

}
