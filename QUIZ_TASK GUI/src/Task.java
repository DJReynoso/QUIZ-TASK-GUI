import java.awt.*;

public class Task {
    private String taskName;
    private String assignedTo;
    public Task(String taskName, String assignedTo) {
        this.taskName = taskName;
        this.assignedTo = assignedTo;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return  "<html> Task: " + this.getTaskName() + "<br>Assigned to: " + this.getAssignedTo();
    }
}
