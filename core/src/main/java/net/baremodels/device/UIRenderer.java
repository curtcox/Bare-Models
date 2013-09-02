package net.baremodels.device;

import net.baremodels.ui.UIComponent;

/**
 * For producing a platform-specific UI.
 */
public interface UIRenderer {
    Object render(UIComponent ui);
}
