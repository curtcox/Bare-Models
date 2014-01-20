package net.baremodels.ui;

import net.baremodels.model.Inspectable;

/**
 * A user interface button.
 * This describes how to present the button to the user and what happens if the user selects it.
 */
public final class UIButton
    implements UIComponent
{

    private final String name;
    private final UIIcon icon;
    private final Inspectable inspectable;

    public UIButton(Inspectable inspectable) {
        this(inspectable,inspectable.toString());
    }

    public UIButton(Inspectable inspectable, String name) {
        this(inspectable,name,null);
    }

    public UIButton(Inspectable inspectable, String name, UIIcon icon) {
        this.inspectable = inspectable;
        this.name = name;
        this.icon = icon;
    }

    @Override
    public Inspectable getInspectable() {
        return inspectable;
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
        return name.equals(that.name) && inspectable.equals(that.inspectable) && areEqual(icon,that.icon);
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
