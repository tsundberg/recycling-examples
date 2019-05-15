package se.thinkcode.todolist;

import java.util.Objects;

public class TodoItem {
    private Owner owner;
    private Task task;

    @SuppressWarnings("unused")
    private TodoItem() {
    }

    public TodoItem(Owner owner, Task task) {
        this.owner = owner;
        this.task = task;
    }

    public Owner getOwner() {
        return owner;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return Objects.equals(owner, todoItem.owner) &&
                Objects.equals(task, todoItem.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, task);
    }

    @Override
    public String toString() {
        return "owner: " + owner + "\n" +
                "task:  " + task + "\n";
    }
}
