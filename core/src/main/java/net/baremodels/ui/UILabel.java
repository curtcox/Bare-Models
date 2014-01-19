package net.baremodels.ui;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;

/**
 * Unlike a button, a label can't be selected.
 */
public final class UILabel
    implements UIComponent
{
    private final String name;

    public UILabel(String name) {
        this.name = name;
    }

    @Override
    public Model getInspectable() {
        return ModelFactory.DEFAULT.of(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UILabel)) {
            return false;
        }
        UILabel that = (UILabel) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
