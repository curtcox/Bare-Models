package net.baremodels.ui;

import net.baremodels.model.Model;

/**
 * A user interface button.
 */
public final class UIButton
    implements UIComponent
{

    private final String name;
    private final Model model;

    public UIButton(Model model) {
        this(model,model.toString());
    }

    public UIButton(Model model, String name) {
        this.model = model;
        this.name = name;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UIButton)) {
            return false;
        }
        UIButton that = (UIButton) o;
        return name.equals(that.name) && model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Button(%s)",name);
    }
}
