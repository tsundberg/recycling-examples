package se.thinkcode.cukenfest.steps;

import se.thinkcode.cukenfest.steps.adapters.AdapterFactory;
import se.thinkcode.cukenfest.steps.adapters.TodoAdapter;
import se.thinkcode.cukenfest.todolist.Owner;
import se.thinkcode.cukenfest.todolist.Task;

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
