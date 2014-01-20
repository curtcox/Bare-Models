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
     * Return the named operation.
     */
    default Operation operation(String name) {
        return operations().get(name);
    }

    /**
     * Return the value of the named property.
     */
    default Object get(String name) {
        return properties().get(name).get();
    }

    /**
     * Listens for changes to a model.
     */
    @FunctionalInterface
    interface Listener {
        void onChange(Model model);
    }
}
