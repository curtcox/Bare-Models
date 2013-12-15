package net.baremodels.runner;

import net.baremodels.model.*;
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
    private final PropertyNameMapper nameMapper;

    public SimpleModelRenderer() {
        this(new SimplePropertyNameMapper());
    }

    public SimpleModelRenderer(PropertyNameMapper nameMapper) {
        this.nameMapper = nameMapper;
    }

    @Override
    public UIContainer render(Model model, NavigationContext context) {
        UIComponent component = render(model);
        return component instanceof UIContainer
                ? (UIContainer) component
                : SimpleUIContainer.of(model,component);
    }

    private UIComponent render(Model model) {
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
        if (Inspectable.NAME.equals(property.name())) {
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
        String name = nameMapper.getName(property);
        if (isList(property)) {
            return new UIButton(property.model(), name);
        }
        return new UIButton(property.model(), name + ": " + property.model().toString());
    }

    private boolean isList(Property property) {
        return property.model() instanceof ListModel;
    }

    private UIComponent labelFor(Property property) {
        return new UILabel(nameMapper.getName(property) + ": " + property.get());
    }

    private UIComponent labelFor(Model model) {
        return new UILabel(model.toString());
    }
}
