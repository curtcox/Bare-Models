package net.baremodels.runner;

import net.baremodels.model.Model;

import java.util.function.Predicate;

/**
 * Displays models until a termination condition is met.
 */
public interface Runner {

    /**
     * Iteratively set the model, until a termination condition is met.
     */
    void setModel(Model model, Predicate<Model> until);

}
