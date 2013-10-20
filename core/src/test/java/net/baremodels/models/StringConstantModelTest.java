package net.baremodels.models;

import net.baremodels.model.Model;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class StringConstantModelTest {

    ModelFactory modelFactory = ModelFactory.DEFAULT;

    @Test
    public void is_a_Model() {
        assertTrue(newTestObject("") instanceof Model);
    }

    private StringConstantModel newTestObject(String value) {
        return new StringConstantModel(value,modelFactory);
    }

    @Test
    public void toString_returns_value_from_constructor() {
        String value = "gqfygWEFUH";
        assertSame(value,newTestObject(value).toString());
    }

    @Test
    public void name_is_same_as_value_from_constructor() {
        String value = "ggtuageught438qfygWEFUH";
        assertSame(value,newTestObject(value).name());
    }

    @Test
    public void are_equal() {
        assertAreEqual(newTestObject("this"), newTestObject("this"));
    }

    @Test
    public void are_not_equal() {
        assertAreNotEqual(newTestObject("this"), newTestObject("that"));
        assertAreNotEqual(newTestObject("this"), "this");
        assertAreNotEqual(newTestObject("this"), null);
    }

    private void assertAreNotEqual(StringConstantModel a, Object b) {
        assertFalse(a.equals(b));
    }

    private void assertAreEqual(StringConstantModel a, StringConstantModel b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertEquals(a.hashCode(),b.hashCode());
    }
}
