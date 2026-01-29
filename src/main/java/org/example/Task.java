package org.example;

public class Task {
   private String name;
   private String priority;
   private boolean completed;

    public Task(String name, String priority){
        this.name = name;
        this.priority = priority;
    }

    public String getName(){
        return name;
    }
    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(String priority) {
        // Remove spaces and convert to lowercase
        String p = priority.trim().toLowerCase();

        if (p.equals("high") || p.equals("medium") || p.equals("low")) {
            this.priority = priority.trim();  // store it nicely without spaces
        } else {
            System.out.println("Invalid priority! Must be High, Medium, or Low.");
        }
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    // @Override is optional but recommended —
    // it tells Java: “I’m intentionally overriding the default Object toString() method”.
    @Override
    public String toString() {
        // This part: (completed ? " [DONE]" : "") is called a ternary operator.
        // It works like a mini if-else:
        //   If completed == true, then it adds " [DONE]" to the string
        //   If completed == false, then it adds nothing (empty string "")
        // This way, completed tasks show "[DONE]" automatically when we print them
        return name + " [" + priority + "]" + (completed ? " [DONE]" : "");
    }

}
