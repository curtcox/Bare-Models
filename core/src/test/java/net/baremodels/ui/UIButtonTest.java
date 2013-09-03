package net.baremodels.ui;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class UIButtonTest {

    @Test
    public void getName_returns_name_from_constructor() {
        String expected = "Fred";
        String actual = new UIButton(expected).getName();
        assertSame(expected,actual);
    }

    @Test
    public void equals() {
        assertEquals(new UIButton("a"),new UIButton("a"));
        assertEquals(new UIButton("b"),new UIButton("b"));
    }

    @Test
    public void hashCode_equals() {
        assertEquals(new UIButton("a").hashCode(),new UIButton("a").hashCode());
        assertEquals(new UIButton("b").hashCode(),new UIButton("b").hashCode());
    }

    @Test
    public void not_equals() {
        assertNotEquals(new UIButton("a"), new UIButton("b"));
        assertNotEquals(new UIButton("b"), new UIButton("a"));
        assertNotEquals(new UIButton("b"), new HashMap());
        assertNotEquals(new UIButton("b"), null);
    }

    private void assertNotEquals(UIButton a, Object b) {
        assertFalse(a.equals(b));
    }

    @Test
    public void toString_returns_button_name() {
        assertEquals("Button(a)",new UIButton("a").toString());
    }
}
