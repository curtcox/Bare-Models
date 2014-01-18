package net.baremodels.model;

/**
 * A property on an object.  Properties provide a mechanism for instance variable access.
 * Getting a property value should never cause a state change.
 * @author curt
 */
public interface Property<T>
    extends Inspectable
{
    /**
     * Return the current value of this property.
     * Getting a property value should never cause a state change.
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
     * For determining if a given property meets certain criteria.
     */
    interface Matcher {
        boolean matches(Property property);
    }
}
