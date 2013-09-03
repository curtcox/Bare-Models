package net.baremodels.swing;

import net.baremodels.ui.UIComponent;

import javax.swing.*;

public class SwingComponentTranslator {

    public JComponent translate(UIComponent ui) {
        JButton button = new JButton();
        button.setName(ui.getName());
        button.setText(ui.getName());
        return button;
    }
}
