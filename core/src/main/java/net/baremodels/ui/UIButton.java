package net.baremodels.ui;

import net.baremodels.model.Model;

/**
 * A user interface button.
 */
public final class UIButton
    implements UIComponent
{

    private final String name;
    private final UIIcon icon;
    private final Model model;

    public UIButton(Model model) {
        this(model,model.toString());
    }

    public UIButton(Model model, String name) {
        this(model,name,null);
    }

    public UIButton(Model model, String name, UIIcon icon) {
        this.model = model;
        this.name = name;
        this.icon = icon;
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
        return name.equals(that.name) && model.equals(that.model) && areEqual(icon,that.icon);
    }

    private boolean areEqual(UIIcon a, UIIcon b) {
        return a==null && b==null ||
               a!=null && b!=null && a.equals(b);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Button(%s)",name);
    }

    public UIIcon getIcon() {
        return icon;
    }
}
