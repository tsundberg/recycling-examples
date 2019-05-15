package se.thinkcode.todolist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDatabase implements Database {
    private Map<Owner, List<Task>> allTasks = new HashMap<>();

    @Override
    public void addTask(Owner owner, Task task) {
        List<Task> tasks = allTasks.getOrDefault(owner, new ArrayList<>());
        tasks.add(task);

        allTasks.put(owner, tasks);
    }

    @Override
    public List<Task> getTasks(Owner owner) {
        return allTasks.getOrDefault(owner, new ArrayList<>());
    }
}
