package net.baremodels.runner;

import net.baremodels.ui.*;

import java.util.Collection;

/**
 * For supplying
 */
public interface WidgetSupplier {

    <T> T button(UIButton ui, UIComponent.Listener listener);

    <T> T container(UIContainer ui, Collection components);

    <T> T list(UIList ui, UIComponent.Listener listener);

    <T> T label(UILabel ui);
}
