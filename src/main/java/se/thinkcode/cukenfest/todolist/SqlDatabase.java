package se.thinkcode.cukenfest.todolist;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class SqlDatabase implements Database {
    private DataSource datasource;

    @Override
    public void addTask(Owner owner, Task task) {
        try {
            TaskDAO dao = getTaskDAO();

            Random random = new SecureRandom();
            String key = "" + random.nextLong();

            String ownerName = owner.getName();
            String taskName = task.getDescription();

            dao.add(key, ownerName, taskName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<Task> getTasks(Owner owner) {
        try {
            TaskDAO dao = getTaskDAO();

            String ownerName = owner.getName();

            return dao.getTasks(ownerName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public SqlDatabase() {
        createDataSource();
    }

    private void createDataSource() {
        PoolProperties p = new PoolProperties();

        p.setUrl("jdbc:h2:mem:todo");
        p.setDriverClassName("org.h2.Driver");

        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(15);
        p.setMaxIdle(p.getMaxActive());
        p.setInitialSize(7);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(2);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        datasource = new DataSource();
        datasource.setPoolProperties(p);

        prepareDatabase();
    }

    private void prepareDatabase() {
        try {
            Flyway flyway = Flyway
                    .configure()
                    .dataSource(datasource)
                    .load();

            flyway.migrate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TaskDAO getTaskDAO() {
        Jdbi jdbi = Jdbi.create(datasource);
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi.onDemand(TaskDAO.class);
    }
}
