package net.baremodels.text;

import net.baremodels.model.ListModel;
import net.baremodels.model.Property;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class TextWidgetSupplier implements WidgetSupplier {

    @Override
    public String button(UIButton ui, UIComponent.Listener listener) {
        return String.format("[%s]",ui.getName());
    }

    @Override
    public String container(UIContainer ui, Collection components) {
        List<String> panel = new ArrayList<>();
        for (Object component : components) {
            panel.add(component.toString());
        }
        return ui.getName() + panel;
    }

    @Override
    public String list(UIList ui, UIComponent.Listener listener) {
        List<String> list = new ArrayList<>();
        ListModel listModel = ui.getModel();
        for (Property item : listModel.properties().values()) {
            list.add(item.model().name());
        }
        return ui.getName() + list;
    }
}
