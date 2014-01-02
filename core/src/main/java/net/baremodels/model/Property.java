package net.baremodels.model;

/**
 * A property on an object.  Properties provide a mechanism for instance variable access.
 * @author curt
 */
public interface Property<T>
    extends Inspectable
{
    /**
     * Return the current glyph of this property.
     */
    T get();

    /**
     * Set the current glyph of this property.
     */
    void set(T t);

    /**
     * Return the current glyph as a model.
     */
    Model model();

    /**
     * For determining if a given property meets certain criteria.
     */
    interface Matcher {
        boolean matches(Property property);
    }
}
