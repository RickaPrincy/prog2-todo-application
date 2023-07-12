package Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Todo {
    private int id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private int priority;
    private boolean done;

    public Todo(int id, String title, String description, LocalDateTime deadline, int priority, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", priority=" + priority +
                ", done=" + done +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo todo)) return false;
        return id == todo.id && priority == todo.priority && done == todo.done && Objects.equals(title, todo.title) && Objects.equals(description, todo.description) && Objects.equals(deadline, todo.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, deadline, priority, done);
    }
}
