package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.runner.modelrenderer.PropertyIconMapper;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;

import java.util.Map;

/**
 * Information that can be used to determine how to present a model.
 */
public interface AppContext
    extends PropertyIconMapper
{

    /**
     * Return the appropriate layout information for this container, given
     * the current device state.
     */
    UILayout layout(UIContainer container, DeviceState deviceState);

    /**
     * Return the configuration map for this context.
     */
    Map<String, String> config();

    /**
     * Something that listens for changes to an AppContext.
     * @author curt
     */
    public interface Listener {
        void onChange(AppContext context);
    }
}
