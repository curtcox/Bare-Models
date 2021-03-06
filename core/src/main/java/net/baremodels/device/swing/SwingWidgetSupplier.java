package net.baremodels.device.swing;

import net.baremodels.device.swing.icon.SwingIconSupplier;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * A WidgetSupplier that returns Swing widgets.
 */
final class SwingWidgetSupplier
    implements WidgetSupplier
{
    final SwingIconSupplier iconSupplier = new SwingIconSupplier();

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
        UIIcon icon = ui.getIcon();
        if (icon!=null) {
            button.setIcon(iconSupplier.getIcon(icon));
        }
        button.setName(ui.getName());
        button.setText(ui.getName());
        button.addActionListener(x -> listener.onSelected(ui.getInspectable()));
        return button;
    }

    @Override
    public JPanel container(UIContainer container, UILayout layout, List components, ComponentConstraintSupplier layoutConstraints) {
        validateSizesMatch(container, components);
        validateConstraints(container, layout);
        JPanel panel = new JPanel((LayoutManager) layoutConstraints.getLayoutManager());
        panel.setName(container.getName());
        for (int i=0; i<container.size(); i++) {
            Object constraints = getConstraints(container, layout, layoutConstraints, i);
            System.out.println(String.format("%s %s",((JComponent) components.get(i)).getName(),constraints));
            panel.add((JComponent) components.get(i), constraints);
        }
        return panel;
    }

    private Object getConstraints(UIContainer container, UILayout layout, ComponentConstraintSupplier layoutConstraints, int i) {
        UIComponent uiComponent = container.get(i);
        UILayout.Constraints uiConstraints = layout.getConstraints(uiComponent);
        return layoutConstraints.getComponentConstraints(uiConstraints);
    }

    private void validateSizesMatch(UIContainer container, List components) {
        if (container.size()!=components.size()) {
            String message = format(
                "Container size must match component size, but %s!=%s.",
                 container.size(),components.size()
            );
            throw new IllegalArgumentException(message);
        }
    }

    private void validateConstraints(UIContainer container, UILayout layout) {
        for (int i=0; i<container.size(); i++) {
            UIComponent uiComponent = container.get(i);
            UILayout.Constraints uiConstraints = layout.getConstraints(uiComponent);
            if (uiConstraints==null) {
                String message = String.format("Missing layout info for [%s]",uiComponent);
                throw new IllegalArgumentException(message);
            }
        }
    }

    @Override
    public JComponent list(UIList ui, UIComponent.Listener listener) {
        List<Model> models = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (Property item : ui.getInspectable().properties().values()) {
            models.add(item.model());
            names.add(item.model().name());
        }
        JList jList = new JList(names.toArray());
        jList.setName(ui.getName());
        jList.addListSelectionListener(x -> listener.onSelected(models.get(x.getFirstIndex())));
        return jList;
    }
}
