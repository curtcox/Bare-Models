package net.baremodels.ui;

import java.util.Map;

/**
 * An immutable toolkit-independent layout.
 */
public final class UILayout {

    private final Map<UIComponent, Constraints> componentConstraints;

    public UILayout(Map<UIComponent, Constraints> componentConstraints) {
        this.componentConstraints = componentConstraints;
    }

    public static final class Constraints {
        public final String value;

        public Constraints(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            Constraints that = (Constraints) o;
            return value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    public Constraints getConstraints(UIComponent component) {
        return componentConstraints.get(component);
    }

}
