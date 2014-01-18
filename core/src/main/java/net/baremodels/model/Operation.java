package net.baremodels.model;

import java.util.List;

/**
 * An operation on an object that is less simple and defined than a property.
 * Often, invoking an operation will produce a state change.
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
     * The returned object should be either a Model or an Intent.
     * Invoking an operation may produce state changes.
     */
    Object invoke();

    /**
     * The standard thing to call the property which specifies if an operation generates an intent.
     */
    String INTENT = "intent";


    /**
     * Return true, if invoking this operation would produce an intent and false otherwise.
     */
    default boolean hasIntent() {
        return (boolean) meta().get(INTENT).get();
    }

}
