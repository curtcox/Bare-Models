package net.baremodels.runner.modelrenderer;

import net.baremodels.model.*;
import net.baremodels.runner.*;
import net.baremodels.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simple implementation of ModelContainerRenderer.
 * For rendering ModelS into UIComponentS.
 */
public final class SimpleModelContainerRenderer
    implements ModelContainerRenderer
{
    /**
     * Property -> name
     */
    private final PropertyNameMapper nameMapper;

    /**
     * Property -> icon
     */
    private final PropertyIconMapper iconMapper;

    public SimpleModelContainerRenderer() {
        this(new SimplePropertyNameMapper(),new SimplePropertyIconMapper());
    }

    public SimpleModelContainerRenderer(PropertyIconMapper iconMapper) {
        this(new SimplePropertyNameMapper(),iconMapper);
    }

    public SimpleModelContainerRenderer(PropertyNameMapper nameMapper, PropertyIconMapper iconMapper) {
        this.nameMapper = nameMapper;
        this.iconMapper = iconMapper;
    }

    @Override
    public UIContainer render(Model model) {
        UIComponent component = renderModel(model);
        return component instanceof UIContainer
                ? (UIContainer) component
                : SimpleUIContainer.of(model,component);
    }

    private UIComponent renderModel(Model model) {
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
        return renderModel(property.model());
    }

    private boolean shouldBeLabel(Property property) {
        Model model = property.model();
        return !(model instanceof ListModel) &&
                model.operations().isEmpty() &&
                model.properties().isEmpty();
    }

    private UIComponent buttonFor(Property property) {
        return new UIButton(property.model(),
                nameMapper.getName(property),
                iconMapper.getIcon(property));
    }

    private UIComponent labelFor(Property property) {
        return new UILabel(nameMapper.getName(property));
    }

    private UIComponent labelFor(Model model) {
        return new UILabel(model.toString());
    }
}
