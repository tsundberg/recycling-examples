package se.thinkcode.steps.adapters;

import se.thinkcode.todolist.Owner;
import se.thinkcode.todolist.Task;

import java.util.List;

public interface TodoAdapter {
    void addTask(Owner owner, Task task);

    List<Task> getTasks(Owner owner);

    void cleanUp();
}
