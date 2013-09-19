package net.baremodels.device;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.ui.UIComponent;

public interface GenericDevice
    extends Intent.Listener
{

    /**
     * Display the given UI and return the selected model.
     * Often, there will be a 1-to-1 relationship between objects and UI components.
     * However, some components may correspond to many objects.
     * List components are one example of this.
     */
    Model display(UIComponent ui);
}
