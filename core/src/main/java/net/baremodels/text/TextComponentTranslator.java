package net.baremodels.text;

import net.baremodels.model.ListModel;
import net.baremodels.model.Property;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UIList;

import java.util.ArrayList;
import java.util.List;

final class TextComponentTranslator {

    public String translate(UIComponent ui) {
        if (ui instanceof UIContainer) {
            return container(ui).toString();
        }
        if (ui instanceof UIList) {
            return list(ui);
        }
        return button(ui);
    }

    private String button(UIComponent ui) {
        return String.format("[%s]",ui.getName());
    }

    private List<String> container(UIComponent ui) {
        UIContainer container = (UIContainer) ui;
        List<String> panel = new ArrayList<>();
        panel.add(ui.getName());
        for (UIComponent component : container) {
            panel.add(translate(component).toString());
        }
        return panel;
    }

    private String list(UIComponent ui) {
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
