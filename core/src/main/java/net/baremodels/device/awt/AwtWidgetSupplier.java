package net.baremodels.device.awt;

import net.baremodels.model.ListModel;
import net.baremodels.model.Property;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.WidgetSupplier;
import net.baremodels.ui.*;

import java.awt.*;

import static java.lang.String.format;

final class AwtWidgetSupplier
    implements WidgetSupplier
{

    @Override
    public Label label(UILabel ui) {
        Label label = new Label(ui.getName());
        label.setName(ui.getName());
        return label;
    }

    @Override
    public Button button(UIButton ui, UIComponent.Listener listener) {
        Button button = new Button();
        button.setName(ui.getName());
        button.setLabel(ui.getName());
        button.addActionListener(e -> listener.onSelected(ui.getInspectable()));
        return button;
    }

    @Override
    public Panel container(UIContainer container, UILayout layout, java.util.List components, ComponentConstraintSupplier layoutConstraints) {
        validateSizesMatch(container, components);
        validateConstraints(container,layout);
        Panel panel = new Panel(layoutConstraints.getLayoutManager());
        panel.setName(container.getName());
        for (int i=0; i<container.size(); i++) {
            String constraints = getConstraints(container, layout, layoutConstraints, i);
            panel.add((Component) components.get(i), constraints);
        }
        return panel;
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

    private String getConstraints(UIContainer container, UILayout layout, ComponentConstraintSupplier layoutConstraints, int i) {
        UIComponent uiComponent = container.get(i);
        UILayout.Constraints uiConstraints = layout.getConstraints(uiComponent);
        return layoutConstraints.getComponentConstraints(uiConstraints);
    }

    private void validateSizesMatch(UIContainer container, java.util.List components) {
        if (container.size()!=components.size()) {
            String message = format(
                    "Container size must match component size, but %s!=%s.",
                    container.size(),components.size()
            );
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public List list(UIList ui, UIComponent.Listener listener) {
        List awtList = new List();
        awtList.setName(ui.getName());
        ListModel listModel = ui.getInspectable();
        for (Property item : listModel.properties().values()) {
            awtList.add(item.model().name());
        }
        awtList.addActionListener(e -> listener.onSelected(listModel.properties().get(awtList.getSelectedIndex()).model()));
        return awtList;
    }

}
