package net.baremodels.runner;

import net.baremodels.model.Model;

import java.util.function.Predicate;

/**
 * Synchronously displays selectable Models on a device until a termination condition is met.
 */
@FunctionalInterface
public interface SyncRunner {

    /**
     * Set the Model to display.
     * This model will be presented to the user through some form of user interface.
     * The user will then select a new model.
     * This process will continue, until a termination condition is met.
     */
    default Model setModel(Model model, Predicate<Model> until) {
        Model current = model;
        while (!until.test(current)) {
            current = display(current);
        }
        return current;
    }

    /**
     * Use some sort of interface to present this Model to the user.
     * If the user picked a model that generated an Intent, then the current
     * Model will be returned.
     * @param current What to display.
     * @return What the user picked.
     */
    Model display(Model current);

}
