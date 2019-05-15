package se.thinkcode.todolist;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task map(ResultSet rs, StatementContext ctx) throws SQLException {
        String taskName = rs.getNString("task");

        return new Task(taskName);
    }
}
