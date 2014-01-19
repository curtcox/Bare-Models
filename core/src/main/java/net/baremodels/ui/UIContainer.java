package net.baremodels.ui;

import net.baremodels.model.Model;

import java.util.List;

/**
 * A UI component that holds other UI components.
 */
public interface UIContainer
    extends UIComponent, List<UIComponent>
{
    Model getInspectable();
}
