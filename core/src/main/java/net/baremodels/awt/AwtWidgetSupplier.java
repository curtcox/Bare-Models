package net.baremodels.awt;

import net.baremodels.model.ListModel;
import net.baremodels.model.Property;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIList;

import java.awt.*;
import java.util.Collection;

final class AwtWidgetSupplier implements WidgetSupplier {

    @Override
    public Component button(UIButton ui, UIComponent.Listener listener) {
        Button button = new Button();
        button.setName(ui.getName());
        button.setLabel(ui.getName());
        button.addActionListener(e -> listener.onSelected(ui.getModel()));
        return button;
    }

    @Override
    public Component container(UIContainer ui, Collection components) {
        Panel panel = new Panel();
        panel.setName(ui.getName());
        for (Object component : components) {
            panel.add((Component) component);
        }
        return panel;
    }

    @Override
    public Component list(UIList ui, UIComponent.Listener listener) {
        List awtList = new List();
        awtList.setName(ui.getName());
        ListModel listModel = ui.getModel();
        for (Property item : listModel.properties().values()) {
            awtList.add(item.model().name());
        }
        awtList.addActionListener(e -> listener.onSelected(listModel.properties().get(awtList.getSelectedIndex()).model()));
        return awtList;
    }

}