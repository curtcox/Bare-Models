package net.baremodels.swing;

import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class SwingWidgetSupplier implements WidgetSupplier {

    @Override
    public JButton button(UIComponent ui, UIComponent.Listener listener) {
        JButton button = new JButton();
        button.setName(ui.getName());
        button.setText(ui.getName());
        button.addActionListener(x -> listener.onSelected(ui.getModel()));
        return button;
    }

    @Override
    public JComponent container(UIComponent ui, Collection components) {
        JPanel panel = new JPanel();
        panel.setName(ui.getName());
        for (Object component : components) {
            panel.add((JComponent) component);
        }
        return panel;
    }

    @Override
    public JComponent list(UIComponent ui, UIComponent.Listener listener) {
        UIList uiList = (UIList) ui;
        net.baremodels.model.ListModel listModel = uiList.getModel();
        List<Model> models = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (Property item : listModel.properties().values()) {
            models.add(item.model());
            names.add(item.model().name());
        }
        JList jList = new JList(names.toArray());
        jList.setName(ui.getName());
        jList.addListSelectionListener(x -> listener.onSelected(models.get(x.getFirstIndex())));
        return jList;
    }
}
