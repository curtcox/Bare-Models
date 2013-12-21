package net.baremodels.ui;

import java.util.Map;

/**
 * A toolkit-independent layout.
 */
public final class UILayout {

    private final Map<UIComponent, Constraints> componentConstraints;

    public UILayout(Map<UIComponent, Constraints> componentConstraints) {
        this.componentConstraints = componentConstraints;
    }

    public static final class Constraints {

    }

    public Constraints getConstraints(UIComponent component) {
        return componentConstraints.get(component);
    }
}
