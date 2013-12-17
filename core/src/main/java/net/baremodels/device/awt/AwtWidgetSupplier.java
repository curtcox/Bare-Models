package net.baremodels.device.awt;

import net.baremodels.model.ListModel;
import net.baremodels.model.Property;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.*;

import java.awt.*;

final class AwtWidgetSupplier
    implements WidgetSupplier
{

    @Override
    public Component label(UILabel ui) {
        Label label = new Label(ui.getName());
        label.setName(ui.getName());
        return label;
    }

    @Override
    public Component button(UIButton ui, UIComponent.Listener listener) {
        Button button = new Button();
        button.setName(ui.getName());
        button.setLabel(ui.getName());
        button.addActionListener(e -> listener.onSelected(ui.getModel()));
        return button;
    }

    @Override
    public Component container(UIContainer ui, UILayout layout, java.util.List components, ComponentConstraintSupplier componentConstraintSupplier) {
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
