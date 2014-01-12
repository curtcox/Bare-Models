package net.baremodels.runner;

import net.baremodels.model.Model;

/**
 * Uses the selected model as the next model.
 */
public final class SelectedNextModelGenerator
    implements NextModelGenerator
{
    @Override
    public Model generateNextModel(Model current, Model selected) {
        return selected;
    }
}
