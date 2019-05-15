package se.thinkcode.todolist;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class TodoListViewResource {
    private TodoList todoList;

    public TodoListViewResource(TodoList todoList) {
        this.todoList = todoList;
    }

    @GET
    public TodoListView getTasks() {
        return new TodoListView();
    }

    @POST
    public TodoListView addTasks(@FormParam("owner") String ownerName,
                                 @FormParam("task") String taskDescription) {
        Owner owner = new Owner(ownerName);

        if (!taskDescription.isEmpty()) {
            Task task = new Task(taskDescription);
            todoList.addTask(owner, task);
        }

        List<Task> tasks = todoList.getTasks(owner);

        return new TodoListView(owner, tasks);
    }
}
