package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;

/**
 * Information that can be used to determine how to present a model.
 */
public interface AppContext {

    /**
     * Return the appropriate layout information for this container, given
     * the current device state.
     */
    UILayout layout(UIContainer container, DeviceState deviceState);

}
