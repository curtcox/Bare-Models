package net.baremodels.models;

import net.baremodels.model.Model;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class FieldPropertyTest {

    static class SampleObject {
        String stringField = "string value";
        int intField = 42;
        double doubleField = 3.1415926535;
        List<String> stringListField = new ArrayList<>();
        transient Object transientField = "transient";
    }

    ModelFactory modelFactory = new ObjectModelFactory();
    SampleObject sample = new SampleObject();

    @Test
    public void toString_contains_field_name() {
        assertTrue(newFieldProperty("stringField").toString().contains("stringField"));
        assertTrue(newFieldProperty("intField").toString().contains("intField"));
    }

    @Test
    public void name_is_field_name() {
        FieldProperty property = newFieldProperty("stringField");
        assertEquals("stringField", property.name());
    }

    @Test
    public void model_is_model_of_field_value() {
        FieldProperty property = newFieldProperty("stringField");
        assertEquals(modelFactory.of("string value"),property.model());
    }

    @Test
    public void string_field() {
        FieldProperty property = newFieldProperty("stringField");
        assertEquals("string value", property.get());
        property.set("Foo");
        assertSame("Foo", property.get());
    }

    @Test
    public void int_field() {
        FieldProperty property = newFieldProperty("intField");
        property.set(42);
        assertEquals(42, property.get());
    }

    @Test
    public void double_field() {
        FieldProperty property = newFieldProperty("doubleField");
        property.set(98.6);
        assertEquals(98.6, (double) property.get(), 0.01);
    }

    @Test
    public void stringList_field_get() {
        FieldProperty property = newFieldProperty("stringListField");
        assertEquals(new ArrayList(),property.get());
        List<String> value = Collections.singletonList("foo");
        property.set(value);
        assertEquals(value, property.get());
    }

    @Test
    public void stringList_field_model() {
        FieldProperty property = newFieldProperty("stringListField");
        Model expected = modelFactory.of(new ArrayList(),"stringListField");
        assertEquals(expected, property.model());
        assertEquals("stringListField",property.name());
        assertEquals("stringListField",property.model().name());
    }

    @Test
    public void stringList_field_model_returns_null_for_elementData() {
        FieldProperty property = newFieldProperty("stringListField");
        try {
            property.model().properties().get(1000).get();
            fail();
        } catch (IndexOutOfBoundsException e) {
        }
    }

    @Test
    public void transient_field_model() {
        FieldProperty property = newFieldProperty("transientField");
        assertEquals("transient", property.get());
    }

    FieldProperty newFieldProperty(String name) {
        for (Field field : SampleObject.class.getDeclaredFields()) {
            if (field.getName().equals(name)) {
                return new FieldProperty(sample,field,modelFactory);
            }
        }
        throw new IllegalArgumentException(name);
    }
}
