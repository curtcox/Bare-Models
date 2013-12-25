package net.baremodels.device;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;

/**
 * A synchronous device, with all of the details abstracted away.
 */
public interface SyncDevice
    extends Intent.Listener
{

    /**
     * Display the given UI and return the selected model.
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
    Model display(UIContainer container, UILayout layout);

    void redisplay(UIContainer container, UILayout layout);

    /**
     * Return the current state of this device.
     */
    DeviceState getDeviceState();
}
