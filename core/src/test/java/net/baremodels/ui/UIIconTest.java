package net.baremodels.ui;

import org.junit.Test;

import static org.junit.Assert.*;

public class UIIconTest {

    @Test
    public void can_create() {
        new UIIcon("");
    }

    @Test
    public void equals_returns_true_when_two_icons_have_the_same_string_value() {
        assertEquals(new UIIcon(""),new UIIcon(""));
    }

    @Test
    public void equals_returns_false_when_two_icons_have_different_string_values() {
        assertFalse(new UIIcon("this").equals(new UIIcon("that")));
    }

    @Test
    public void hashCode_returns_same_value_when_two_icons_have_the_same_string_value() {
        assertEquals(new UIIcon("").hashCode(),new UIIcon("").hashCode());
    }

}
