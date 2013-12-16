package net.baremodels.runner;

import net.baremodels.ui.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Translates UIContainersS into widgets provided by the WidgetSupplier.
 */
public final class SimpleContainerTranslator
    implements ContainerTranslator
{
    /**
     * Supplies the toolkit-specific widgets.
     */
    final WidgetSupplier widgetSupplier;

    /**
     * Supplies layout constraints for components.
     */
    final ComponentConstraintSupplier componentConstraints;


    /**
     * Create a new translator, given a toolkit-specific widget widgetSupplier.
     */
    public SimpleContainerTranslator(WidgetSupplier supplier, ComponentConstraintSupplier componentConstraints) {
        this.widgetSupplier = supplier;
        this.componentConstraints = componentConstraints;
    }

    /**
     * Translate the given toolkit independent UI widget into an actual toolkit-specific widget.
     */
    <T> T translateComponent(UIComponent component, UILayout layout, UIComponent.Listener listener) {
        if (component instanceof UIContainer) {
            return translate((UIContainer) component, layout, listener);
        }
        if (component instanceof UIList) {
            return widgetSupplier.list((UIList)component, listener);
        }
        if (component instanceof UIButton) {
            return widgetSupplier.button((UIButton)component,listener);
        }
        return widgetSupplier.label((UILabel) component);
    }

    public <T> T translate(UIContainer container, UILayout layout, UIComponent.Listener listener) {
        List components = container.stream().map(x -> translateComponent(x, layout, listener)).collect(Collectors.toList());
        return widgetSupplier.container(container, layout, components, componentConstraints);
    }

}
