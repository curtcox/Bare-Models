package net.baremodels.models;

import org.junit.Test;

import java.lang.reflect.Parameter;

import static org.junit.Assert.assertEquals;

public class ParameterPropertyTest {

    ModelFactory modelFactory = new ObjectModelFactory();

    static class SampleObject {
         void sample(String name, int age, double temp){}
    }

    @Test
    public void properties_contains_name_for_name() {
        ParameterProperty property = newParameterProperty(0);
        assertEquals("name",property.name());
    }

    @Test
    public void properties_contains_name_for_age() {
        ParameterProperty property = newParameterProperty(1);
        assertEquals("age",property.name());
    }

    @Test
    public void properties_contains_name_for_temp() {
        ParameterProperty property = newParameterProperty(2);
        assertEquals("temp",property.name());
    }

    @Test
    public void can_set_name() {
        ParameterProperty property = newParameterProperty(0);
        property.set("foo");
        assertEquals("foo", property.get());
    }

    @Test
    public void can_get_name_model() {
        ParameterProperty property = newParameterProperty(0);
        property.set("foo");
        assertEquals(modelFactory.of("foo"), property.model());
    }

    @Test
    public void can_set_age() {
        ParameterProperty property = newParameterProperty(1);
        property.set(42);
        assertEquals(42, property.get());
    }

    @Test
    public void can_set_temp() {
        ParameterProperty property = newParameterProperty(2);
        property.set(98.6);
        assertEquals(98.6, (double) property.get(), 0.01);
    }

    private ParameterProperty newParameterProperty(int index) {
        Parameter parameter = SampleObject.class.getDeclaredMethods()[0].getParameters()[index];
        return new ParameterProperty(parameter,modelFactory);
    }
}
