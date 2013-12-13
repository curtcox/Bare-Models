package net.baremodels.device.swing;

import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A WidgetSupplier that returns Swing widgets.
 */
final class SwingWidgetSupplier
    implements WidgetSupplier
{

    @Override
    public JLabel label(UILabel ui) {
        String text = ui.getName();
        int width = 800;
        String labelText = String.format("<html><div WIDTH=%d>%s</div><html>", width, text);
        JLabel label = new JLabel(labelText);
        label.setName(ui.getName());
        return label;
    }

    @Override
    public JButton button(UIButton ui, UIComponent.Listener listener) {
        JButton button = new JButton();
        button.setName(ui.getName());
        button.setText(ui.getName());
        button.addActionListener(x -> listener.onSelected(ui.getModel()));
        return button;
    }

    @Override
    public JComponent container(UIContainer ui, Collection components, ComponentConstraintSupplier layout) {
        JPanel panel = new JPanel(layout.getLayoutManager());
        panel.setName(ui.getName());
        for (Object component : components) {
            panel.add((JComponent) component, layout.getComponentConstraints(component));
        }
        return panel;
    }

    @Override
    public JComponent list(UIList ui, UIComponent.Listener listener) {
        List<Model> models = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (Property item : ui.getModel().properties().values()) {
            models.add(item.model());
            names.add(item.model().name());
        }
        JList jList = new JList(names.toArray());
        jList.setName(ui.getName());
        jList.addListSelectionListener(x -> listener.onSelected(models.get(x.getFirstIndex())));
        return jList;
    }
}
