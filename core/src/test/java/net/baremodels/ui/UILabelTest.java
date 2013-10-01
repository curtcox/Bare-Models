package net.baremodels.ui;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class UILabelTest {

    String name = "Fred";

    @Test
    public void getName_returns_name_from_constructor() {
        String actual = new UILabel(name).getName();
        assertSame(name,actual);
    }

    @Test
    public void equals() {
        assertEquals(new UILabel("teams"),new UILabel("teams"));
        assertEquals(new UILabel("users"),new UILabel("users"));
    }

    @Test
    public void hashCode_equals() {
        assertEquals(new UILabel("teams").hashCode(),new UILabel("teams").hashCode());
        assertEquals(new UILabel("users").hashCode(),new UILabel("users").hashCode());
    }

    @Test
    public void not_equals() {
        assertNotEquals(new UILabel("teams"), new UILabel("users"));
        assertNotEquals(new UILabel("users"), new UILabel("teams"));
        assertNotEquals(new UILabel("users"), new HashMap());
        assertNotEquals(new UILabel("users"), null);
    }

    private void assertNotEquals(UILabel a, Object b) {
        assertFalse(a.equals(b));
    }

    @Test
    public void toString_returns_label_name() {
        assertEquals("teams",new UILabel("teams").toString());
    }
}
