package net.baremodels.runner;

import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIList;

import java.util.Collection;

/**
 */
public interface WidgetSupplier {

    <T> T button(UIButton ui, UIComponent.Listener listener);

    <T> T container(UIContainer ui, Collection components);

    <T> T list(UIList ui, UIComponent.Listener listener);
}
