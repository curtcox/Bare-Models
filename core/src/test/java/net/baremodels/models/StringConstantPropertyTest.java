package net.baremodels.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringConstantPropertyTest {

    ModelFactory modelFactory = new ObjectModelFactory();
    String name = "uhgr y734672136hyh";
    String value = "urfhwauwfghgr yug fyhgdyh";
    StringConstantProperty testObject = new StringConstantProperty(name,value,modelFactory);

    @Test
    public void get_uses_value_from_constructor() {
        assertSame(value, testObject.get());
    }

    @Test
    public void toString_uses_value_from_constructor() {
        assertSame(value, testObject.toString());
    }

    @Test
    public void name_uses_value_from_constructor() {
        assertSame(name, testObject.name());
    }

    @Test
    public void cannot_be_set() {
        try {
            testObject.set("different");
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals("Cannot be set",e.getMessage());
        }
    }

    @Test
    public void model_returns_ObjectModel() {
        assertTrue(testObject.model() instanceof StringConstantModel);
    }

    @Test
    public void equals() {
        assertEquals(new StringConstantProperty(name,value,modelFactory),testObject);
    }

    @Test
    public void not_equals() {
        assertFalse(new StringConstantProperty(name,"other",modelFactory).equals(testObject));
    }

    @Test
    public void hashCode_uses_value_hashCode() {
        assertEquals(value.hashCode(),testObject.hashCode());
    }
}
