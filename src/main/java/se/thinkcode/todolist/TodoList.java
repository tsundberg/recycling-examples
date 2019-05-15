package se.thinkcode.todolist;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private Database database;

    public TodoList(Database database) {
        this.database = database;
    }

    public void addTask(Owner owner, Task task) {
        database.addTask(owner, task);
    }

    public List<Task> getTasks(Owner owner) {
        List<Task> tasks = database.getTasks(owner);

        if (tasks == null) {
            return new ArrayList<>();
        } else {
            return tasks;
        }
    }
}
