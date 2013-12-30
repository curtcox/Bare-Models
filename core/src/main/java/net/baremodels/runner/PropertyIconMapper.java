package net.baremodels.runner;

import net.baremodels.model.Property;

import java.net.URL;

/**
 * Maps from a Property to the icon presented to a user.
 */
public interface PropertyIconMapper {
    URL getIcon(Property property);
}
