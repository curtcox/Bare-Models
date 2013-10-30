package net.baremodels.ui;

import net.baremodels.model.Model;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class UILabelTest {

    private UILabel newLabel(String name) {
        return new UILabel(name);
    }

    @Test
    public void getName_returns_name_from_constructor() {
        String name = "Fred";
        String actual = newLabel(name).getName();
        assertSame(name,actual);
    }

    @Test
    public void equals() {
        assertEquals(newLabel("teams"),newLabel("teams"));
        assertEquals(newLabel("users"),newLabel("users"));
    }

    @Test
    public void hashCode_equals() {
        assertEquals(newLabel("teams").hashCode(),newLabel("teams").hashCode());
        assertEquals(newLabel("users").hashCode(),newLabel("users").hashCode());
    }

    @Test
    public void not_equals() {
        assertNotEquals(newLabel("teams"), newLabel("users"));
        assertNotEquals(newLabel("users"), newLabel("teams"));
        assertNotEquals(newLabel("users"), new HashMap());
        assertNotEquals(newLabel("users"), null);
    }

    private void assertNotEquals(UILabel a, Object b) {
        assertFalse(a.equals(b));
    }

    @Test
    public void toString_returns_label_name() {
        assertEquals("teams",newLabel("teams").toString());
    }

    @Test
    public void getModel_returns_StringModel_with_value_from_constructor() {
        UILabel testObject = newLabel("teams");
        Model model = testObject.getModel();
        assertEquals("teams",model.toString());
        assertEquals("teams",model.name());
    }
}
