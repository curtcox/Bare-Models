package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;

import java.util.HashMap;

/**
 * A simple implementation of AppContext.
 */
public final class SimpleAppContext
    implements AppContext
{

    @Override
    public UILayout layout(UIContainer ui, DeviceState deviceState) {
        return new UILayout(new HashMap<>());
    }
}
