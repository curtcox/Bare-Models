package net.baremodels.awt;

import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;

import java.awt.*;

public class AwtComponentTranslator {

    public Component translate(UIComponent ui) {
        if (ui instanceof UIContainer) {
            return container(ui);
        }
        Button button = new Button();
        button.setName(ui.getName());
        button.setLabel(ui.getName());
        return button;
    }

    private Container container(UIComponent ui) {
        UIContainer container = (UIContainer) ui;
        Panel panel = new Panel();
        panel.setName(ui.getName());
        for (UIComponent component : container) {
            panel.add(translate(component));
        }
        return panel;
    }
}
