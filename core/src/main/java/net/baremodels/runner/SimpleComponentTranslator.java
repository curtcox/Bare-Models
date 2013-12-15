package net.baremodels.runner;

import net.baremodels.ui.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Translates UIComponentS into widgets provided by the WidgetSupplier.
 */
public final class SimpleComponentTranslator
    implements ComponentTranslator
{

    /**
     * Supplies the toolkit-specific widgets.
     */
    final WidgetSupplier supplier;

    final ComponentConstraintSupplier componentConstraintSupplier;


    /**
     * Create a new translator, given a toolkit-specific widget supplier.
     */
    public SimpleComponentTranslator(WidgetSupplier supplier, ComponentConstraintSupplier componentConstraintSupplier) {
        this.supplier = supplier;
        this.componentConstraintSupplier = componentConstraintSupplier;
    }

    /**
     * Translate the given toolkit independent UI widget into an actual toolkit-specific widget.
     */
    <T> T translateComponent(UIComponent ui, UILayout layout, UIComponent.Listener listener) {
        if (ui instanceof UIContainer) {
            return translate((UIContainer) ui, layout, listener);
        }
        if (ui instanceof UIList) {
            return supplier.list((UIList)ui, listener);
        }
        if (ui instanceof UIButton) {
            return supplier.button((UIButton)ui,listener);
        }
        return supplier.label((UILabel) ui);
    }

    public <T> T translate(UIContainer ui, UILayout layout, UIComponent.Listener listener) {
        List components = ui.stream().map(x -> translateComponent(x, layout, listener)).collect(Collectors.toList());
        return supplier.container(ui,components, componentConstraintSupplier);
    }

}
