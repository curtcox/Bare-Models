package net.baremodels.ui;

/**
 * A user interface icon.
 */
public final class UIIcon {

    public final UIGlyph glyph;

    public UIIcon(UIGlyph glyph) {
        this.glyph = glyph;
    }

    @Override
    public boolean equals(Object o) {
        UIIcon that = (UIIcon) o;
        return glyph.equals(that.glyph);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
