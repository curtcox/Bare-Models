package net.baremodels.model;

/**
 * A property on an object.  Properties provide a mechanism for instance variable access.
 * @author curt
 */
public interface Property<T>
    extends Inspectable
{
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

}
