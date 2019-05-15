package se.thinkcode.todolist;

import io.dropwizard.views.View;

import java.util.ArrayList;
import java.util.List;

public class TodoListView extends View {
    private Owner owner;
    private List<Task> tasks = new ArrayList<>();

    TodoListView() {
        super("todoList.mustache");
    }

    TodoListView(Owner owner, List<Task> tasks) {
        super("todoList.mustache");
        this.owner = owner;
        this.tasks = tasks;
    }

    public Owner getOwner() {
        return owner;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}