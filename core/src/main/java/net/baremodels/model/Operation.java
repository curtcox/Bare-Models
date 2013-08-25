package net.baremodels.model;

import net.baremodels.intent.Intent;

import java.util.List;
import java.util.Map;

/**
 * An operation on an object that is less simple and defined than a property.
 */
public interface Operation {

    /**
     * Values required to invoke an operation.
     */
    List<Property> arguments();

    /**
     * Perform the action and produce the result.
     */
    Object invoke();

    /**
     * Return information about this operation.
     * Use this to obtain the name of this operation, along with any other metadata about it.
     */
    Map<String,Object> properties();

}
