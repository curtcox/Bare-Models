package net.baremodels.model;

import java.util.Map;

/**
 * A property on an object.  Properties provide a mechanism for instance variable access.
 * @author curt
 */
public interface Property<T> {
    /**
     * Return the current value of this property.
     */
    T get();

    /**
     * Set the current value of this property.
     */
    void set(T t);

    /**
     * Return the current value as a model.
     */
    Model model();

    /**
     * Return information about this property.
     * Use this to obtain the name of this property, along with any other metadata about it.
     */
    Map<String,Property> meta();

    /**
     * The standard thing to call the property which specifies the name of something.
     */
    String NAME = "name";

    default String name() {
        return (String) meta().get(Property.NAME).get();
    }
}
