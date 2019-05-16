
# Recycle your examples

## Rewrite the steps to use a delegator

```
package se.thinkcode.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import se.thinkcode.todolist.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoSteps {
    private Delegator delegator = new Delegator();

    @Given("that {} is out of {}")
    public void that_is_out_of(String name, String item) {
        delegator.createTodoList(name);
    }

    @When("he adds {} to his todo list")
    public void he_adds_a_task_to_his_todo_list(String task) {
        delegator.addTask(task);
    }

    @Then("should {} todo list contain {}")
    public void should_Thomas_todo_list_contain_buy_cat_food(String name, String expectedTask) {
        Task expected = new Task(expectedTask);

        List<Task> actual = delegator.getTasks(name);

        assertThat(actual).containsExactly(expected);
    }
}
```

## Implement the delegator

```
package se.thinkcode.steps;

import se.thinkcode.steps.adaptors.AdapterFactory;
import se.thinkcode.steps.adaptors.TodoAdapter;
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
}
```

## Create the interface each adapter much implement 

```
package se.thinkcode.steps.adapters;

import se.thinkcode.todolist.Owner;
import se.thinkcode.todolist.Task;

import java.util.List;

public interface TodoAdapter {
    void addTask(Owner owner, Task task);

    List<Task> getTasks(Owner owner);
}
```

## The delegator uses an adapter factory for getting adaptors

```
package se.thinkcode.steps.adapters;

public class AdapterFactory {
    public TodoAdapter getAdapter() {
        String seam = "";

        if (System.getProperty("seam") != null) {
            seam = System.getProperty("seam");
        }

        if (seam.equals("model")) {
            return new ModelAdapter();
        }

        throw new RuntimeException("Didn't understand which seam you want to use");
    }
}
```

## Implement the model adapter

```
package se.thinkcode.steps.adapters;

import se.thinkcode.todolist.Database;
import se.thinkcode.todolist.Owner;
import se.thinkcode.todolist.Task;
import se.thinkcode.todolist.TodoList;

import java.util.List;

public class ModelAdapter implements TodoAdapter {
    private TodoList todoList;

    ModelAdapter() {
        todoList = new TodoList();
    }

    @Override
    public void addTask(Owner owner, Task task) {
        todoList.addTask(owner, task);
    }

    @Override
    public List<Task> getTasks(Owner owner) {
        return todoList.getTasks(owner);
    }
}
```

## Run using gradle with a proper seam

    ./gradlew clean cucumber -Dseam=model

