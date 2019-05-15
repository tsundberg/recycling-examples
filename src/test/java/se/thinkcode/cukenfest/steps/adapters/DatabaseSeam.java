package se.thinkcode.cukenfest.steps.adapters;

import java.util.Objects;

class DatabaseSeam {
    private String database;

    DatabaseSeam(String database) {
        this.database = database;
    }

    public String getDatabase() {
        return database;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseSeam seam = (DatabaseSeam) o;
        return Objects.equals(database, seam.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database);
    }

    @Override
    public String toString() {
        return database;
    }
}
