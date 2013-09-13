package net.baremodels.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class TypedValueTest {

    String value = "ufghuiwgUYIWFGYUH";
    TypedValue testObject = typedValue(value);

    @Test
    public void toString_uses_value_from_constructor() {
        assertSame(value,testObject.toString());
    }

    @Test
    public void equal_values_are_equal() {
        are_equal(value, value);
        are_equal(null, null);
    }

    @Test
    public void unequal_values_are_not_equal() {
        are_not_equal("", "a");
    }

    @Test
    public void different_types_of_values_are_not_equal_even_when_their_string_values_are() {
        TypedValue a = new TypedValue("") {};
        TypedValue b = new TypedValue("") {};
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    @Test
    public void is_not_equal_to_null() {
        assertFalse(typedValue("").equals(null));
    }

    @Test
    public void hashCode_returns_value_hashCode_when_not_null() {
        assertEquals(value.hashCode(),testObject.hashCode());
    }

    @Test
    public void hashCode_returns_0_when_null() {
        assertEquals(0,typedValue(null).hashCode());
    }

    private void are_not_equal(String a, String b) {
        assertFalse(typedValue(a).equals(typedValue(b)));
        assertFalse(typedValue(b).equals(typedValue(a)));
    }

    private void are_equal(String a, String b) {
        assertEquals(typedValue(a), typedValue(a));
        assertEquals(typedValue(b), typedValue(b));
        assertEquals(typedValue(a), typedValue(b));
        assertEquals(typedValue(b), typedValue(a));
    }

    private TypedValue typedValue(String value) {
        return new TypedValue(value) {};
    }
}
