package net.baremodels.device;

import net.baremodels.intent.Intent;
import net.baremodels.ui.UIContainer;

/**
 * An asynchronous device, with all of the details abstracted away.
 */
public interface AsyncDevice
    extends Intent.Listener
{
    /**
     * Display the given UI on the device.
     * Eventually the user will select a component with an underlying model.
     * The model will be used to produce a UI component, which brings us full circle.
     * <p>
     * Often, there will be a 1-to-1 relationship between objects and UI components.
     * However,
     * <ol>
     *   <li>
     *       Some components may correspond to many objects.
     *       List components are one example of this.
     *   </li>
     *   <li>
     *       Some components may correspond to zero objects.
     *       Label components are one example of this.
     *   </li>
     * </ol>
     */

    void display(UIContainer ui);
}
