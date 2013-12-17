package net.baremodels.device.text;

import net.baremodels.model.ListModel;
import net.baremodels.model.Property;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.*;

import java.util.ArrayList;
import java.util.List;

public final class TextWidgetSupplier
    implements WidgetSupplier
{

    @Override
    public String label(UILabel ui) {
        return String.format("%s",ui.getName());
    }

    @Override
    public String button(UIButton ui, UIComponent.Listener listener) {
        return String.format("[%s]",ui.getName());
    }

    @Override
    public String container(UIContainer ui, UILayout layout, List components, ComponentConstraintSupplier supplier) {
        List<String> panel = new ArrayList<>();
        for (Object component : components) {
            panel.add((String)component);
        }
        return String.format("%s",panel);
    }

    @Override
    public String list(UIList ui, UIComponent.Listener listener) {
        List<String> list = new ArrayList<>();
        ListModel listModel = ui.getModel();
        for (Property item : listModel.properties().values()) {
            list.add(item.model().name());
        }
        return ui.getName() + list;
    }
}
