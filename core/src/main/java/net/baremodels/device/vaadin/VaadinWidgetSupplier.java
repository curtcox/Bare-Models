package net.baremodels.device.vaadin;

import com.vaadin.ui.*;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.*;

import java.util.ArrayList;
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
        //button.addListener((Button.ClickListener) x -> listener.onSelected(container.getInspectable()));
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                System.out.println("Clicked " + ui);
                listener.onSelected(ui.getInspectable());
            }
        });
        return button;
    }

    @Override
    public ComponentContainer container(UIContainer ui, UILayout layout, List components, ComponentConstraintSupplier componentConstraintSupplier) {
        FormLayout formLayout = componentConstraintSupplier.getLayoutManager();
        formLayout.setId(ui.getName());
        for (Object component : components) {
            formLayout.addComponent((Component) component);
        }
        return formLayout;
    }

    @Override
    public Component list(UIList ui, UIComponent.Listener listener) {
        net.baremodels.model.ListModel listModel = ui.getInspectable();
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
