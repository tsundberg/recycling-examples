package se.thinkcode.cukenfest.steps.adapters;

import se.thinkcode.cukenfest.todolist.Database;
import se.thinkcode.cukenfest.todolist.Owner;
import se.thinkcode.cukenfest.todolist.Task;
import se.thinkcode.cukenfest.todolist.TodoList;

import java.util.List;

public class ModelAdapter implements TodoAdapter {
    private TodoList todoList;

    ModelAdapter(Database database) {
        todoList = new TodoList(database);
    }

    @Override
    public void addTask(Owner owner, Task task) {
        todoList.addTask(owner, task);
    }

    @Override
    public List<Task> getTasks(Owner owner) {
        return todoList.getTasks(owner);
    }

    @Override
    public void cleanUp() {
        // no op until we need to do something here
    }
}
