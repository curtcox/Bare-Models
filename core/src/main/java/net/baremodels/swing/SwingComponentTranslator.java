package net.baremodels.swing;

import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;

import javax.swing.*;

public class SwingComponentTranslator {

    public JComponent translate(UIComponent ui) {
        if (ui instanceof UIContainer) {
            return container(ui);
        }
        JButton button = new JButton();
        button.setName(ui.getName());
        button.setText(ui.getName());
        return button;
    }

    private JComponent container(UIComponent ui) {
        UIContainer container = (UIContainer) ui;
        JPanel panel = new JPanel();
        panel.setName(ui.getName());
        for (UIComponent component : container) {
            panel.add(translate(component));
        }
        return panel;
    }
}
