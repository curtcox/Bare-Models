package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;

public final class SimpleModelRenderer implements ModelRenderer {
    @Override
    public UIComponent render(Model model) {
        SimpleUIContainer container = new SimpleUIContainer();
        for (Property property : model.properties().values()) {
            String name = property.name();
            container.add(new UIButton(name));
        }
        return container;
    }
}
