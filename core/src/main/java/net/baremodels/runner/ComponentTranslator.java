package net.baremodels.runner;

import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;

/**
 * Translates UIComponentS into widgets provided by the WidgetSupplier.
 */
public interface ComponentTranslator {

    /**
     * Translate the given toolkit independent UI widget into an actual toolkit-specific widget.
     */
    <T> T translate(UIContainer ui, UIComponent.Listener listener);
}
