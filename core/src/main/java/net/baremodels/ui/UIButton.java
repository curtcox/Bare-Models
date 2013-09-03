package net.baremodels.ui;

public final class UIButton
    implements UIComponent
{

    private final String name;

    public UIButton(String name) {
        this.name = name;
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
        return name.equals(that.name);
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
