package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.ui.UIContainer;

/**
 * For rendering BrowserS as UIContainerS.
 */
public final class BrowserModelContainerRenderer
    implements ModelContainerRenderer
{
    private final ModelContainerRenderer containerRenderer;

    public BrowserModelContainerRenderer(ModelContainerRenderer containerRenderer) {
        this.containerRenderer = containerRenderer;
    }

    @Override
    public UIContainer render(Model model) {
        String message = String.format("The given Model must be for a Browser, but is not [%s]",model);
        throw new IllegalArgumentException(message);
    }
}
