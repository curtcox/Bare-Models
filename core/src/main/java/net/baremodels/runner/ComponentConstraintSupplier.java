package net.baremodels.runner;

import net.baremodels.ui.UILayout.Constraints;

/**
 * Provides toolkit-specific layout managers and component constraints.
 */
public interface ComponentConstraintSupplier {

    <T> T getLayoutManager();

    /**
     * Return the layout manager constraints for the given toolkit-independent constraint.
     */
    String getComponentConstraints(Constraints constraints);
}
