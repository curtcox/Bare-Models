package net.baremodels.common;

import java.util.Objects;

/**
 * Like strings, but more type safe.
 */
public abstract class TypedValue {

    private final String name;

    public TypedValue(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }
        TypedValue that = (TypedValue) other;
        return Objects.equals(name,that.name);
    }

    @Override
    public int hashCode() {
        return name==null ? 0 : name.hashCode();
    }
}
