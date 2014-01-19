package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.model.Inspectable;
import net.baremodels.model.Model;

import java.util.function.Predicate;

/**
 * Synchronously displays selectable Models on a device until a termination condition is met.
 * <p>
 * Devices, need to process state change events while displaying the UI, so
 * generally, you will want to use an AsyncRunner instead.
 * However, this interface can be useful for testing and reasoning about application state.
 */
public interface SyncRunner
    extends DeviceState.Listener, AppContext.Listener, NextModelGenerator
{

    /**
     * Set the Model to display.
     * This model will be presented to the user through some form of user interface.
     * The user will then select a new model.
     * This process will continue, until a termination condition is met.
     */
    default Model setModel(Model model, Predicate<Model> until) {
        Model current = model;
        while (!until.test(current)) {
            Inspectable selected = display(current);
            current = generateNextModel(current,selected);
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
    Inspectable display(Model current);

}
