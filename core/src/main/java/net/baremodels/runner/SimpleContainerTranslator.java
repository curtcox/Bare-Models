package net.baremodels.runner;

import net.baremodels.ui.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Translates UIComponentS into widgets provided by the WidgetSupplier.
 */
public final class SimpleContainerTranslator
    implements ContainerTranslator
{

    /**
     * Supplies the toolkit-specific widgets.
     */
    final WidgetSupplier supplier;

    final ComponentConstraintSupplier componentConstraintSupplier;


    /**
     * Create a new translator, given a toolkit-specific widget supplier.
     */
    public SimpleContainerTranslator(WidgetSupplier supplier, ComponentConstraintSupplier componentConstraintSupplier) {
        this.supplier = supplier;
        this.componentConstraintSupplier = componentConstraintSupplier;
    }

    /**
     * Translate the given toolkit independent UI widget into an actual toolkit-specific widget.
     */
    <T> T translateComponent(UIComponent component, UILayout layout, UIComponent.Listener listener) {
        if (component instanceof UIContainer) {
            return translate((UIContainer) component, layout, listener);
        }
        if (component instanceof UIList) {
            return supplier.list((UIList)component, listener);
        }
        if (component instanceof UIButton) {
            return supplier.button((UIButton)component,listener);
        }
        return supplier.label((UILabel) component);
    }

    public <T> T translate(UIContainer container, UILayout layout, UIComponent.Listener listener) {
        List components = container.stream().map(x -> translateComponent(x, layout, listener)).collect(Collectors.toList());
        return supplier.container(container,components, componentConstraintSupplier);
    }

}
