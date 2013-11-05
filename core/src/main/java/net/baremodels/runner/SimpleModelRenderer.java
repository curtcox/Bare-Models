package net.baremodels.runner;

import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simple implementation of ModelRenderer.
 */
public final class SimpleModelRenderer
    implements ModelRenderer
{

    @Override
    public UIComponent render(Model model) {
        System.out.println(String.format("Rendering %s:%s",model.getClass(),model));
        if (model instanceof ListModel) {
            return renderListModel((ListModel) model);
        }
        if (model.properties().isEmpty()) {
            return labelFor(model);
        }
        return renderNormalModel(model);
    }

    private UIComponent renderListModel(ListModel model) {
        return new UIList(model,model.name());
    }

    private UIComponent renderNormalModel(Model model) {
        List<UIComponent> components = new ArrayList<>();
        components.add(new UILabel(model.name()));
        components.addAll(Arrays.asList(componentsForProperties(model)));
        return SimpleUIContainer.of(model, components.toArray(new UIComponent[0]));
    }

    private UIComponent[] componentsForProperties(Model model) {
        return model.properties().values().stream()
                    .map(p -> componentFor(model, p))
                    .filter(c -> c != null)
                    .toArray(x -> new UIComponent[x]);
    }

    private UIComponent componentFor(Model model, Property property) {
        if (Property.NAME.equals(property.name())) {
            return null;
        }
        if (shouldBeLabel(property)) {
            return labelFor(property);
        }
        if (model.properties().values().size()>2) {
            return buttonFor(property);
        }
        return render(property.model());
    }

    private boolean shouldBeLabel(Property property) {
        Model model = property.model();
        return !(model instanceof ListModel) &&
                model.operations().isEmpty() &&
                model.properties().isEmpty();
    }

    private UIComponent buttonFor(Property property) {
        return new UIButton(property.model(), property.name());
    }

    private UIComponent labelFor(Property property) {
        return new UILabel(property.name() + ": " + property.get());
    }

    private UIComponent labelFor(Model model) {
        return new UILabel(model.toString());
    }
}
