package net.baremodels.device.vaadin;

import com.vaadin.ui.*;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A WidgetSupplier that returns Vaadin widgets.
 */
final class VaadinWidgetSupplier
    implements WidgetSupplier
{

    @Override
    public TextField label(UILabel ui) {
        String text = ui.getName();
        TextField label = new TextField(text);
        label.setId(ui.getName());
        return label;
    }

    @Override
    public Button button(UIButton ui, UIComponent.Listener listener) {
        Button button = new Button(ui.getName());
        button.setId(ui.getName());
        //button.addListener((Button.ClickListener) x -> listener.onSelected(ui.getModel()));
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                System.out.println("Clicked " + ui);
                listener.onSelected(ui.getModel());
            }
        });
        return button;
    }

    @Override
    public ComponentContainer container(UIContainer ui, Collection components) {
        FormLayout layout = new FormLayout();
        layout.setId(ui.getName());
        for (Object component : components) {
            layout.addComponent((Component) component);
        }
        return layout;
    }

    @Override
    public Component list(UIList ui, UIComponent.Listener listener) {
        net.baremodels.model.ListModel listModel = ui.getModel();
        List<Model> models = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (Property item : listModel.properties().values()) {
            models.add(item.model());
            names.add(item.model().name());
        }
        Table table = new Table();
        table.setId(ui.getName());
//        table.addListSelectionListener(x -> listener.onSelected(models.get(x.getFirstIndex())));
        return table;
    }
}
