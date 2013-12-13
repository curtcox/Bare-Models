package net.baremodels.runner;

/**
 * Provides toolkit-specific layout managers and component constraints.
 */
public interface ComponentConstraintSupplier {

    <T> T getLayoutManager();

    /**
     * Return the layout manager constraints for the given component.
     */
    String getComponentConstraints(Object component);
}
