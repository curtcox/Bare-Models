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

    <T> T label(UILabel ui);

    <T> T button(UIButton ui, UIComponent.Listener listener);

    <T> T list(UIList ui, UIComponent.Listener listener);

    <T> T container(UIContainer ui, Collection components);
}
