package net.baremodels.model;

import java.util.List;

/**
 * An operation on an object that is less simple and defined than a property.
 */
public interface Operation
    extends Inspectable
{

    /**
     * Values required to invoke an operation.
     */
    List<Property> arguments();

    /**
     * Perform the action and produce the result.
     * The returned object should be a Model or an Intent.
     */
    Object invoke();

}
