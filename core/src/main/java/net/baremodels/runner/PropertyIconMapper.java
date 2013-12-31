package net.baremodels.runner;

import net.baremodels.model.Property;
import net.baremodels.ui.UIIcon;

/**
 * Maps from a Property to the icon presented to a user.
 */
public interface PropertyIconMapper {
    UIIcon getIcon(Property property);
}
