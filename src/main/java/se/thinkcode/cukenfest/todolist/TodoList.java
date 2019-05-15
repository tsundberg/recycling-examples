package se.thinkcode.cukenfest.todolist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoList {
    private Map<Owner, List<Task>> allTasks = new HashMap<>();

    public void addTask(Owner owner, Task task) {
        List<Task> tasks = allTasks.getOrDefault(owner, new ArrayList<>());
        tasks.add(task);

        allTasks.put(owner, tasks);
    }

    public List<Task> getTasks(Owner owner) {
        List<Task> tasks = allTasks.getOrDefault(owner, new ArrayList<>());

        if (tasks == null) {
            return new ArrayList<>();
        } else {
            return tasks;
        }
    }
}