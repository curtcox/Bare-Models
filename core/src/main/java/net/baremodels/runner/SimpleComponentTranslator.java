package net.baremodels.runner;

import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIList;

import java.util.ArrayList;
import java.util.List;

public final class SimpleComponentTranslator {

    final WidgetSupplier supplier;

    public SimpleComponentTranslator(WidgetSupplier supplier) {
        this.supplier = supplier;
    }

    public <T> T translate(UIComponent ui, UIComponent.Listener listener) {
        if (ui instanceof UIContainer) {
            return container(ui,listener);
        }
        if (ui instanceof UIList) {
            return supplier.list(ui, listener);
        }
        return supplier.button(ui,listener);
    }

    private <T> T container(UIComponent ui, UIComponent.Listener listener) {
        UIContainer container = (UIContainer) ui;
        List components = new ArrayList<>();
        for (UIComponent component : container) {
            components.add(translate(component, listener));
        }
        return supplier.container(ui,components);
    }

}
