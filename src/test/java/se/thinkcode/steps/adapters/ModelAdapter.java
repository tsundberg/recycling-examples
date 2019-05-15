package se.thinkcode.steps.adapters;

import se.thinkcode.todolist.Database;
import se.thinkcode.todolist.Owner;
import se.thinkcode.todolist.Task;
import se.thinkcode.todolist.TodoList;

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
