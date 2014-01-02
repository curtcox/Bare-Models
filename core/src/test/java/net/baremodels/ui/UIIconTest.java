package net.baremodels.ui;

import org.junit.Test;

import static org.junit.Assert.*;

public class UIIconTest {

    @Test
    public void can_create() {
        new UIIcon(UIGlyph.user);
    }

    @Test
    public void uses_value_from_constructor() {
        UIGlyph value = UIGlyph.user;
        assertSame(value,new UIIcon(value).glyph);
    }

    @Test
    public void equals_returns_true_when_two_icons_have_the_same_glyph() {
        assertEquals(new UIIcon(UIGlyph.user),new UIIcon(UIGlyph.user));
    }

    @Test
    public void equals_returns_false_when_two_icons_have_different_glyphs() {
        assertFalse(new UIIcon(UIGlyph.adjust).equals(new UIIcon(UIGlyph.user)));
    }

    @Test
    public void hashCode_returns_same_value_when_two_icons_have_the_same_glyph() {
        assertEquals(new UIIcon(UIGlyph.adjust).hashCode(),new UIIcon(UIGlyph.adjust).hashCode());
    }

}
