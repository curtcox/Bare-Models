package net.baremodels.runner;

import net.baremodels.model.Property;

/**
 * Maps from a Property to the name presented to a user.
 * This exists as an interface, because different coding conventions
 * can lead to different desired mappings.
 */
public interface PropertyNameMapper {
    String getName(Property property);
}
