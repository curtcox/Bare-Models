package net.baremodels.models;

import net.baremodels.model.Model;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

public class BooleanConstantModelTest {

    ModelFactory modelFactory = ModelFactory.DEFAULT;

    private BooleanConstantModel newTestObject(boolean value) {
        return BooleanConstantModel.of(value,modelFactory);
    }

    @Test
    public void is_a_Model() {
        assertTrue(newTestObject(true) instanceof Model);
    }

    @Test
    public void toString_returns_value_from_constructor() {
        assertEquals("true",newTestObject(true).toString());
        assertEquals("false",newTestObject(false).toString());
    }

    @Test
    public void name_is_same_as_value_from_constructor() {
        assertSame("true",newTestObject(true).name());
        assertSame("false",newTestObject(false).name());
    }

    @Test
    public void are_equal() {
        assertAreEqual(newTestObject(true), newTestObject(true));
        assertAreEqual(newTestObject(false), newTestObject(false));
    }

    @Test
    public void are_not_equal() {
        assertAreNotEqual(newTestObject(true), newTestObject(false));
        assertAreNotEqual(newTestObject(false), newTestObject(true));
        assertAreNotEqual(newTestObject(true), "this");
        assertAreNotEqual(newTestObject(true), null);
    }

    private void assertAreNotEqual(BooleanConstantModel a, Object b) {
        assertFalse(a.equals(b));
    }

    private void assertAreEqual(BooleanConstantModel a, BooleanConstantModel b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    public void has_0_properties() {
        assertEquals(0,newTestObject(true).properties().size());
    }

    @Test
    public void has_0_operations() {
        assertEquals(0,newTestObject(true).operations().size());
    }

}
