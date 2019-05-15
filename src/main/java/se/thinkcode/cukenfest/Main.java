package se.thinkcode.cukenfest;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import se.thinkcode.cukenfest.todolist.*;

public class Main extends Application<Configuration> {
    private Database database;

    public static void main(String... args) throws Exception {
        new Main().run(args);
    }

    @Override
    public String getName() {
        return "The ToDo List";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<>());
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        database = getDatabase();
        TodoList todoList = new TodoList(database);
        TodoResource todoResource = new TodoResource(todoList);

        environment.jersey().register(todoResource);

        TodoListViewResource todoListViewResource = new TodoListViewResource(todoList);
        environment.jersey().register(todoListViewResource);
    }

    private Database getDatabase() {
        if (database == null) {
            return new SqlDatabase();
        }

        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
