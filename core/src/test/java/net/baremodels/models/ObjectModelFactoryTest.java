package net.baremodels.models;

import net.baremodels.apps.Nucleus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectModelFactoryTest {

    ObjectModelFactory testObject = new ObjectModelFactory();

    @Test
    public void of_returns_NullModel_for_null() {
        assertTrue(testObject.of(null) instanceof NullModel);
    }

    @Test
    public void named_of_returns_NullModel_for_null() {
        assertTrue(testObject.of(null,"name") instanceof NullModel);
    }

    @Test
    public void of_returns_same_object_when_given_same_object() {
        assertSameObjectFrom("Foo");
        assertSameObjectFrom(new Nucleus());
        assertSameObjectFrom(new ArrayList());
    }

    private void assertSameObjectFrom(Object object) {
        assertSame(testObject.of(object), testObject.of(object));
    }

    @Test
    public void of_returns_different_object_when_given_different_object() {
        assertDifferentObjectFrom(new Nucleus(), new Nucleus());
        assertDifferentObjectFrom(new ArrayList(), new ArrayList());
    }

    private void assertDifferentObjectFrom(Object a, Object b) {
        assertFalse(String.format("Should produce different selectable for %s and %s", a, b), testObject.of(a) == testObject.of(b));
    }

    @Test
    public void of_returns_StringConstantModel_when_given_string() {
        assertTrue(testObject.of("foo") instanceof StringConstantModel);
        assertTrue(testObject.of("bar") instanceof StringConstantModel);
    }

    @Test
    public void of_returns_BooleanConstantModel_when_given_boolean() {
        assertTrue(testObject.of(true) instanceof BooleanConstantModel);
        assertTrue(testObject.of(false) instanceof BooleanConstantModel);
        assertTrue(testObject.of(Boolean.TRUE) instanceof BooleanConstantModel);
        assertTrue(testObject.of(Boolean.FALSE) instanceof BooleanConstantModel);
    }

    @Test
    public void of_returns_same_object_when_given_different_equivalent_strings() {
        List parts = Arrays.asList("1", "two", "shoe");
        String a = parts.toString();
        String b = parts.toString();
        assertSame(testObject.of(a),testObject.of(b));
    }

    @Test
    public void of_returns_same_object_when_given_different_equivalent_booleans() {
        assertSame(testObject.of(true),testObject.of(true));
        assertSame(testObject.of(false),testObject.of(false));
    }

    @Test
    public void can_create_a_model() {
        assertNotNull(testObject.of("Value"));
    }

    @Test
    public void returns_ListModel_for_Lists() {
        assertTrue(testObject.of(new ArrayList()) instanceof ObjectListModel);
    }

    @Test
    public void returns_model_with_same_toString_as_given_value() {
        String value = "sehuwehuh";
        assertSame(value,testObject.of(value).toString());
    }

    @Test
    public void returns_model_with_same_name_as_given_value() {
        String value = "g6781g69hy";
        assertSame(value,testObject.of(value).name());
    }

}
