package net.baremodels.runner;

import net.baremodels.ui.*;

import java.util.Collection;

/**
 * For supplying widgets.
 * A given WidgetSupplier will accept toolkit independent UI components and
 * produce the equivalent widgets for a specific toolkit.
 * <p>
 * There are currently suppliers for AWT, Swing, JavaFX, and Vaadin.
 */
public interface WidgetSupplier {

    /**
     * Return the equivalent toolkit-specific label.
     */
    <T> T label(UILabel label);

    /**
     * Return the equivalent toolkit-specific button.
     */
    <T> T button(UIButton button, UIComponent.Listener listener);

    /**
     * Return the equivalent toolkit-specific list.
     */
    <T> T list(UIList list, UIComponent.Listener listener);

    /**
     * Return the equivalent toolkit-specific container.
     */
    <T> T container(UIContainer container, UILayout layout, Collection components, ComponentConstraintSupplier layoutProvider);
}
