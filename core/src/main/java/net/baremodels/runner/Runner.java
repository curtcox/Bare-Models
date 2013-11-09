package net.baremodels.runner;

import net.baremodels.model.Model;

import java.util.function.Predicate;

/**
 * Displays selectable Models until a termination condition is met.
 */
public interface Runner {

    /**
     * Set the Model to display.
     * This model will be presented to the user through some form of user interface.
     * The user will then select a new model.
     * This process will continue, until a termination condition is met.
     */
    void setModel(Model model, Predicate<Model> until);

}
