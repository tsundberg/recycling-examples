package se.thinkcode.cukenfest.steps.adapters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import se.thinkcode.cukenfest.Main;
import se.thinkcode.cukenfest.todolist.Database;
import se.thinkcode.cukenfest.todolist.Owner;
import se.thinkcode.cukenfest.todolist.Task;

import java.util.ArrayList;
import java.util.List;

public class WebAdapter implements TodoAdapter {
    private final WebDriver browser;

    WebAdapter(WebDriver browser, Database database) {
        this.browser = browser;

        startApplication(database);
    }

    @Override
    public void addTask(Owner owner, Task task) {
        browser.get("http://localhost:8080");

        WebElement ownerField = browser.findElement(By.id("owner"));
        ownerField.sendKeys(owner.getName());

        WebElement taskField = browser.findElement(By.id("task"));
        taskField.sendKeys(task.getDescription());

        WebElement submitButton = browser.findElement(By.id("submit"));
        submitButton.click();
    }

    @Override
    public List<Task> getTasks(Owner owner) {
        List<Task> tasks = new ArrayList<>();
        List<WebElement> taskElements = browser.findElements(By.id("taskElement"));
        
        for (WebElement taskItem : taskElements) {
            String description = taskItem.getText();
            Task task = new Task(description);
            tasks.add(task);
        }

        return tasks;
    }

    @Override
    public void cleanUp() {
        browser.close();
    }

    private void startApplication(Database database) {
        try {
            Main main = new Main();
            main.setDatabase(database);

            String[] arguments = {"server"};
            main.run(arguments);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
