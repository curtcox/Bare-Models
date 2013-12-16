package net.baremodels.runner;

import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;

/**
 * Translates toolkit-independent UIContainerS into an actual toolkit-specific containers.
 * Implementations of this interface are device specific.
 */
public interface ContainerTranslator {

    /**
     * Translate the given toolkit-independent UI container into an actual toolkit-specific container.
     */
    <T> T translate(UIContainer container, UILayout layout, UIComponent.Listener listener);
}
