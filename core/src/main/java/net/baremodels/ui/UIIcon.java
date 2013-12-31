package net.baremodels.ui;

/**
 * A user interface icon.
 */
public final class UIIcon {

    public final String value;

    public UIIcon(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        UIIcon that = (UIIcon) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
