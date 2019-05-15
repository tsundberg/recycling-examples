package se.thinkcode.todolist;

import java.util.List;

public interface Database {
    void addTask(Owner owner, Task task);

    List<Task> getTasks(Owner owner);
}
