package net.baremodels.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class BooleanConstantPropertyTest {

    ModelFactory modelFactory = new ObjectModelFactory();
    String name = "moogah";
    boolean value = true;
    BooleanConstantProperty testObject = new BooleanConstantProperty(name,value,modelFactory);

    @Test
    public void get_uses_value_from_constructor() {
        assertTrue((Boolean) new BooleanConstantProperty(name,true,modelFactory).get());
        assertFalse((Boolean) new BooleanConstantProperty(name,false,modelFactory).get());
    }

    @Test
    public void toString_uses_value_from_constructor() {
        assertEquals("true", testObject.toString());
    }

    @Test
    public void name_uses_value_from_constructor() {
        assertSame(name, testObject.name());
    }

    @Test
    public void cannot_be_set() {
        try {
            testObject.set(false);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals("Cannot be set",e.getMessage());
        }
    }

    @Test
    public void model_returns_ObjectModel() {
        assertTrue(testObject.model() instanceof BooleanConstantModel);
    }

    @Test
    public void equals() {
        assertEquals(new BooleanConstantProperty(name,value,modelFactory),testObject);
    }

    @Test
    public void not_equals() {
        assertFalse(new BooleanConstantProperty(name,false,modelFactory).equals(testObject));
    }

}
