package net.baremodels.runner;

import net.baremodels.ui.UILayout.Constraints;

/**
 * Provides toolkit-specific layout managers and component constraints.
 */
public interface ComponentConstraintSupplier {

    /**
     * Return the layout manager for use in laying out a container.
     */
    <T> T getLayoutManager();

    /**
     * Return the layout manager constraints for the given toolkit-independent constraint.
     * Many, but not all, layout managers use StringS for constraints.
     * GridBagLayout, uses GridBagConstraints.
     */
    <T> T getComponentConstraints(Constraints constraints);
}
