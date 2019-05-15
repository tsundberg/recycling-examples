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

    @After
    public void cleanUp(){
        delegator.cleanUp();
    }
}
