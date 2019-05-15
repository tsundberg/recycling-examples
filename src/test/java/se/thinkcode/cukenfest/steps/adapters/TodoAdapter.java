package se.thinkcode.cukenfest.steps.adapters;

import se.thinkcode.cukenfest.todolist.Owner;
import se.thinkcode.cukenfest.todolist.Task;

import java.util.List;

public interface TodoAdapter {
    void addTask(Owner owner, Task task);

    List<Task> getTasks(Owner owner);

    void cleanUp();
}
