package se.thinkcode.todolist;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface TaskDAO {
    @SqlUpdate("insert into todoItems " +
            "(key, owner, task) " +
            "values " +
            "(:key, :owner, :task)")
    void add(@Bind("key") String key,
             @Bind("owner") String owner,
             @Bind("task") String task);

    @SqlQuery("select task from todoItems where owner = :owner")
    @RegisterRowMapper(TaskMapper.class)
    List<Task> getTasks(@Bind("owner") String owner);
}
