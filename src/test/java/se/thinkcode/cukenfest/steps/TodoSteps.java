package se.thinkcode.cukenfest.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import se.thinkcode.cukenfest.todolist.Owner;
import se.thinkcode.cukenfest.todolist.Task;
import se.thinkcode.cukenfest.todolist.TodoList;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoSteps {
    private TodoList todoList;
    private Owner owner;

    @Given("that {} is out of {}")
    public void that_is_out_of(String name, String item) {
        todoList = new TodoList();
        owner = new Owner(name);
    }

    @When("he adds {} to his todo list")
    public void he_adds_a_task_to_his_todo_list(String tasktaskDescription) {
        Task task = new Task(tasktaskDescription);
        todoList.addTask(owner, task);
    }

    @Then("should {} todo list contain {}")
    public void should_Thomas_todo_list_contain_buy_cat_food(String name, String expectedTask) {
        Task expected = new Task(expectedTask);
        Owner owner = new Owner(name);

        List<Task> actual = todoList.getTasks(owner);

        assertThat(actual).containsExactly(expected);
    }
}
