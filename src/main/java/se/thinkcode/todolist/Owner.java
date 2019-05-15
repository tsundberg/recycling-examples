package se.thinkcode.todolist;

import java.util.Objects;

public class Owner {
    private String name;

    @SuppressWarnings("unused")
    private Owner() {
    }

    public Owner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner1 = (Owner) o;
        return Objects.equals(name, owner1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
