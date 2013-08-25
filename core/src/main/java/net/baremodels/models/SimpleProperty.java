package net.baremodels.models;


import net.baremodels.model.Property;

import java.util.Map;
import java.util.Objects;

/**
 * Simple implementation of Property.
 * @author curt
 */
final class SimpleProperty<T>
    implements Property<T>
{
    private T value;
    private final Map<String,Object> properties;
    
    SimpleProperty(Map<String,Object> properties) {
        this(properties,null);
    }

    SimpleProperty(Map<String,Object> properties, T value) {
        this.properties = properties;
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return value==null ? "null" : value.toString();
    }

    public T get() {
        return value;
    }
    
    final public Map<String,Object> properties() {
        return properties;
    }
    
    @Override
    final public boolean equals(Object o) {
        if (this==o) {
            return true;
        }
        SimpleProperty that = (SimpleProperty) o;
        return getClass().equals(that.getClass()) &&
               Objects.equals(properties, that.properties) &&
               Objects.equals(value,that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties, value);
    }

}
