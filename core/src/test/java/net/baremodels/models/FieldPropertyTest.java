package net.baremodels.models;

import net.baremodels.model.Property;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class FieldPropertyTest {

    static class SampleObject {
        String stringField;
        int intField;
        double doubleField;
    }

    SampleObject sample = new SampleObject();

    @Test
    public void fieldName() {
        FieldProperty property = newFieldProperty("stringField");
        assertEquals("stringField", property.properties().get(Property.NAME));
    }

    @Test
    public void stringField() {
        FieldProperty property = newFieldProperty("stringField");
        property.set("Foo");
        assertSame("Foo", property.get());
    }

    @Test
    public void intField() {
        FieldProperty property = newFieldProperty("intField");
        property.set(42);
        assertEquals(42, property.get());
    }

    @Test
    public void doubleField() {
        FieldProperty property = newFieldProperty("doubleField");
        property.set(98.6);
        assertEquals(98.6, (double) property.get(), 0.01);
    }

    FieldProperty newFieldProperty(String name) {
        for (Field field : SampleObject.class.getDeclaredFields()) {
            if (field.getName().equals(name)) {
                return new FieldProperty(sample,field);
            }
        }
        throw new IllegalArgumentException(name);
    }
}
