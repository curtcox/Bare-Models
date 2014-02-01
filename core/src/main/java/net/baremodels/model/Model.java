package net.baremodels.model;

import java.util.Map;

/**
 * An object that can be queried at runtime about its meta and operations.
 * <p>
 * The intent is to support user interfaces that focus on one object at a time.
 * A model is queried about its meta and operations.  The resulting information is used to determine what
 * user interface needs to be constructed.  After the user interacts with the interface, this process is repeated.
 * </p>
 * Methods needed to support the domain models do not belong on this interface (Model).
 * Instead, they belong on the actual domain object that this interface is used to interact with.
 * Objects that implement this interface are generated from domain models.
 * @author curt
 */
public interface Model
    extends Inspectable
{

    /**
     * Return a map of the meta of this model.
     * If this model represents an "ordinary" object, then the keys will be property names.
     * If this model represents an array or collections, then the keys will be indexes.
     */
    Map<?,Property> properties();

    /**
     * Return a map of the operations of this model.
     * Operations roughly correspond to methods.
     */
    Map<?,Operation> operations();

    /**
     * Return the named operation, if it exists, or throw an IllegalArgumentException if it doesn't.
     */
    default Operation operation(String name) {
        if (!operations().keySet().contains(name)) {
            String message = String.format("The operation %s is not defined on %s",name,this);
            throw new IllegalArgumentException(message);
        }
        return operations().get(name);
    }

    /**
     * Return the value of the named property, if it exists, or throw an IllegalArgumentException if it doesn't.
     */
    default Object get(String name) {
        if (!properties().keySet().contains(name)) {
            String message = String.format("The property %s is not defined on %s",name,this);
            throw new IllegalArgumentException(message);
        }
        return properties().get(name).get();
    }

    /**
     * Set the value of the named property, if it exists, or throw an IllegalArgumentException if it doesn't.
     */
    default void set(String name, Object value) {
        if (!properties().keySet().contains(name)) {
            String message = String.format("The property %s is not defined on %s",name,this);
            throw new IllegalArgumentException(message);
        }
        properties().get(name).set(value);
    }

    /**
     * Listens for changes to a model.
     */
    @FunctionalInterface
    interface Listener {
        void onChange(Model model);
    }
}
