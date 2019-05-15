package se.thinkcode.todolist;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("todo")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private TodoList todoList;

    public TodoResource(TodoList todoList) {
        this.todoList = todoList;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response orderPickup(TodoItem todoItem) {
        Owner owner = todoItem.getOwner();
        Task task = todoItem.getTask();
        todoList.addTask(owner, task);

        return Response.status(Response.Status.CREATED)
                 .build();
    }

    @GET
    @Path("/{owner}")
    public List<Task> getAllTasks(@PathParam("owner") Owner owner) {
        return todoList.getTasks(owner);
    }
}
