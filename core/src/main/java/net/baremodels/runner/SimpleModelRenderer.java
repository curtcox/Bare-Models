package net.baremodels.runner;

import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SimpleModelRenderer
    implements ModelRenderer
{

    @Override
    public UIComponent render(Model model) {
        System.out.println("Rendering model" + model);
        if (model instanceof ListModel) {
            return new UIList((ListModel)model,"");
        }
        List<UIComponent> components = new ArrayList<>();
        components.add(new UILabel(model.name()));
        components.addAll(Arrays.asList(componentsForProperties(model)));
        return SimpleUIContainer.of(model,components.toArray(new UIComponent[0]));
    }

    private UIComponent[] componentsForProperties(Model model) {
        return model.properties().values().stream()
                    .map(p -> componentFor(p))
                    .toArray(x -> new UIComponent[x]);
    }

    private UIComponent componentFor(Property property) {
        System.out.println("component for " + property + ":" + property.getClass() + ":" + property.name()+ "=" + property.get());
        if (Property.NAME.equals(property.name())) {
            return labelFor(property);
        }
        return buttonFor(property);
    }

    private UIComponent buttonFor(Property property) {
        Model model = property.model();
        String name = property.name();
        return new UIButton(model,name);
    }

    private UIComponent labelFor(Property property) {
        return new UILabel((String) property.get());
    }
}
