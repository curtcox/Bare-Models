package net.baremodels.text;

import net.baremodels.model.ListModel;
import net.baremodels.model.Property;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class TextWidgetSupplier implements WidgetSupplier {

    @Override
    public String button(UIComponent ui, UIComponent.Listener listener) {
        return String.format("[%s]",ui.getName());
    }

    @Override
    public String container(UIComponent ui, Collection components) {
        List<String> panel = new ArrayList<>();
        panel.add(ui.getName());
        for (Object component : components) {
            panel.add(component.toString());
        }
        return panel.toString();
    }

    @Override
    public String list(UIComponent ui, UIComponent.Listener listener) {
        UIList uiList = (UIList) ui;
        List<String> list = new ArrayList<>();
        list.add(ui.getName());
        ListModel listModel = uiList.getModel();
        for (Property item : listModel.properties().values()) {
            list.add(item.model().name());
        }
        return list.toString();
    }
}
