import lombok.Getter;

import java.util.Comparator;


enum Status {
    ASSIGNED, IN_PROGRESS, NOT_ASSIGNED;

    public String toString() {
        return this.name().replaceAll("_", " ");
    }
}

enum Priority {HIGH, MEDIUM, LOW}
@Getter
public class Task implements Comparable<Task> {
    private String assignee;
    private String projectName;
    private String description;
    private Status status;
    private Priority priority;
    public Task(String assignee, String projectName, String description, Status status, Priority priority) {
        this.assignee = assignee;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public static Comparator<Task> sortByNameAndDescription = Comparator.comparing(Task::getProjectName).thenComparing(Task::getDescription);

    public Task(String assignee, String projectName, String description, Priority priority) {
        this(assignee, projectName, description, assignee == null ? Status.NOT_ASSIGNED : Status.ASSIGNED, priority );
    }


    @Override
    public int compareTo(Task o) {
        int result = this.projectName.compareTo(o.projectName);
        result = result == 0 ? this.description.compareTo(o.description) : result;
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;

        if (!projectName.equals(task.projectName)) return false;
        return description.equals(task.description);
    }

    @Override
    public int hashCode() {
        int result = projectName.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-25s %-25s %-15s %s", assignee, projectName, description, priority, status);
    }
}


