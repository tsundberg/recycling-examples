package se.thinkcode.steps;

import se.thinkcode.steps.adapters.AdapterFactory;
import se.thinkcode.steps.adapters.TodoAdapter;
import se.thinkcode.todolist.Owner;
import se.thinkcode.todolist.Task;

import java.util.List;

class Delegator {
    private TodoAdapter adapter;
    private Owner currentOwner;

    Delegator() {
        AdapterFactory factory = new AdapterFactory();
        adapter = factory.getAdapter();
    }

    void createTodoList(String owner) {
        currentOwner = new Owner(owner);
    }

    void addTask(String description) {
        Task task = new Task(description);
        adapter.addTask(currentOwner, task);
    }

    List<Task> getTasks(String ownerName) {
        Owner owner = new Owner(ownerName);
        return adapter.getTasks(owner);
    }

    void cleanUp() {
        adapter.cleanUp();
    }
}
