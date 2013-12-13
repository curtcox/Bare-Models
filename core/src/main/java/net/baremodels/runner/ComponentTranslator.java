package net.baremodels.runner;

import net.baremodels.ui.UIComponent;

/**
 * Translates UIComponentS into widgets provided by the WidgetSupplier.
 */
public interface ComponentTranslator {

    /**
     * Translate the given toolkit independent UI widget into an actual toolkit-specific widget.
     */
    <T> T translate(UIComponent ui, UIComponent.Listener listener);
}
