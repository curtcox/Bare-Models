package net.baremodels.model;

import java.util.Map;

/**
 * @author curt
 */
public interface Inspectable {

    /**
     * Return information about this object.
     * Use this to obtain the name of this object, along with any other metadata about it.
     */
    Map<String,Property> meta();

    /**
     * The standard thing to call the property which specifies the name of something.
     */
    String NAME = "name";

    /**
     * Return the name of this object.
     */
    default String name() {
        return (String) meta().get(Property.NAME).get();
    }
}
