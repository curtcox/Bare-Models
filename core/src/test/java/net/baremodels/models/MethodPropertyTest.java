package net.baremodels.models;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class MethodPropertyTest {

    @Test
    public void get_returns_value_from_method_when_method_is_toString() throws Exception {
        Object value = new Object();
        String expected = value.toString();
        Method method = Object.class.getMethod("toString",new Class[0]);

        MethodProperty testObject = new MethodProperty(value,method);
        Object actual = testObject.get();

        assertEquals(expected, actual);
    }

    @Test
    public void get_returns_value_from_method() throws Exception {
        Object value = new Object();
        int expected = value.hashCode();
        Method method = Object.class.getMethod("hashCode",new Class[0]);

        MethodProperty testObject = new MethodProperty(value,method);
        Object actual = testObject.get();

        assertEquals(expected, actual);
    }

}
