package net.baremodels.runner;

import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIList;

public final class SimpleModelRenderer implements ModelRenderer {
    @Override
    public UIComponent render(Model model) {
        System.out.println("Rendering model" + model);
        if (model instanceof ListModel) {
            return new UIList((ListModel)model,"");
        }
        SimpleUIContainer container = SimpleUIContainer.of(model);
        for (Property property : model.properties().values()) {
            addComponentForProperty(container, property);
        }
        return container;
    }

    private void addComponentForProperty(SimpleUIContainer container, Property property) {
        Model model = property.model();
        String name = property.name();
        container.add(new UIButton(model,name));
    }
}
