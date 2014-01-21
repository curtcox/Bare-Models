package net.baremodels.runner.modelrenderer;

import net.baremodels.model.Property;
import net.baremodels.ui.UIIcon;

/**
 * Maps from a Property to the icon presented to a user.
 */
public interface PropertyIconMapper {
    /**
     * Return the desired icon, or null, to indicate no preference.
     */
    UIIcon getIcon(Property property);
}
