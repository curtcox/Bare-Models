package net.baremodels.awt;

import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIList;

import java.awt.*;

final class AwtComponentTranslator {

    public Component translate(UIComponent ui, UIComponent.Listener listener) {
        if (ui instanceof UIContainer) {
            return container(ui,listener);
        }
        if (ui instanceof UIList) {
            return list(ui, listener);
        }
        return button(ui,listener);
    }

    private Component button(UIComponent ui, UIComponent.Listener listener) {
        Button button = new Button();
        button.setName(ui.getName());
        button.setLabel(ui.getName());
        button.addActionListener(e -> listener.onSelected(ui));
        return button;
    }

    private Container container(UIComponent ui,UIComponent.Listener listener) {
        UIContainer container = (UIContainer) ui;
        Panel panel = new Panel();
        panel.setName(ui.getName());
        for (UIComponent component : container) {
            panel.add(translate(component,listener));
        }
        return panel;
    }

    private Component list(UIComponent ui,UIComponent.Listener listener) {
        UIList uiList = (UIList) ui;
        java.awt.List awtList = new java.awt.List();
        awtList.setName(ui.getName());
        for (Object item : uiList.getModel().list) {
            awtList.add(item.toString());
        }
        awtList.addActionListener(e -> listener.onSelected(ui));
        return awtList;
    }

}
