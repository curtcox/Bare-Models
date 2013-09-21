package net.baremodels.runner;

import net.baremodels.ui.UIComponent;

import java.util.Collection;

/**
 */
public interface WidgetSupplier {

    <T> T button(UIComponent ui, UIComponent.Listener listener);

    <T> T container(UIComponent ui, Collection components);

    <T> T list(UIComponent ui, UIComponent.Listener listener);
}
