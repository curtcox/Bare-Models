package net.baremodels.models;

import net.baremodels.apps.Nucleus;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ObjectModelFactoryTest {

    ObjectModelFactory testObject = new ObjectModelFactory();

    @Test
    public void of_rejects_null() {
        try {
            testObject.of(null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("null should be used instead of ObjectModel.of(null,unnamed)",e.getMessage());
        }
    }

    @Test
    public void named_of_rejects_null_and_uses_name_in_exception() {
        try {
            testObject.of(null,"supplied_name");
            fail();
        } catch (NullPointerException e) {
            assertEquals("null should be used instead of ObjectModel.of(null,supplied_name)",e.getMessage());
        }
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
        assertFalse(String.format("Should produce different models for %s and %s", a, b), testObject.of(a) == testObject.of(b));
    }

    @Test
    public void can_create_a_model() {
        assertNotNull(testObject.of("Value"));
    }

    @Test
    public void returns_ListModel_for_Lists() {
        assertTrue(testObject.of(new ArrayList()) instanceof ObjectListModel);
    }

}
