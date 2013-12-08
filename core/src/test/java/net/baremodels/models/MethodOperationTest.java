package net.baremodels.models;

import net.baremodels.intent.Intent;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;
import org.junit.Before;
import org.junit.Test;
import test.models.Car;
import test.models.Key;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class MethodOperationTest {

    static class HiddenIntent extends Intent {
        HiddenIntent() { super(null); }
    }

    static class SampleObject {
        String invoked = "";
        void no_args() {
            invoked = "no_args";
        }
        void three_args(String a, int b, double c) {
            invoked = "three args a=" + a + ". b=" + b + ". c=" + c + ".";
        }
        String returns_object() {
            return "Foo!";
        }
        Intent returns_intent() {
            return new HiddenIntent(){};
        }
    }

    SampleObject sample;

    @Before
    public void before() {
        sample = new SampleObject();
    }

    @Test
    public void method_with_no_args_can_be_invoked() {
        MethodOperation method = newMethodOperation("no_args");
        method.invoke();
        assertEquals("no_args",sample.invoked);
    }

    @Test
    public void method_with_no_args_has_proper_name() {
        MethodOperation method = newMethodOperation("no_args");
        assertEquals("no_args",method.name());
        assertEquals("no_args",method.meta().get(Property.NAME).name());
    }

    @Test
    public void void_method_returns_null() {
        MethodOperation method = newMethodOperation("no_args");
        Object result = method.invoke();
        assertEquals(null, result);
    }

    @Test
    public void method_with_no_args_has_proper_count() {
        MethodOperation method = newMethodOperation("no_args");
        assertEquals(0,method.arguments().size());
    }

    @Test
    public void method_with_three_args_has_proper_count() {
        MethodOperation method = newMethodOperation("three_args");
        assertEquals(3,method.arguments().size());
    }

    @Test
    public void method_with_three_args_can_be_invoked() {
        MethodOperation method = newMethodOperation("three_args");
        method.arguments().get(0).set("foo");
        method.arguments().get(1).set(42);
        method.arguments().get(2).set(98.6);
        method.invoke();
        assertEquals("three args a=foo. b=42. c=98.6.", sample.invoked);
    }

    @Test
    public void method_that_returns_intent_returns_intent() {
        MethodOperation method = newMethodOperation("returns_intent");
        Object result = method.invoke();
        assertTrue(result instanceof HiddenIntent);
    }

    @Test
    public void method_that_has_intent_hasIntent_true() {
        MethodOperation method = newMethodOperation("returns_intent");
        assertTrue(method.hasIntent());
        assertTrue((Boolean) method.meta().get(Operation.INTENT).get());
    }

    @Test
    public void key_start_intent_hasIntent_true() throws Exception {
        Car car = new Car();
        Key key = new Key(car);
        Method start = key.getClass().getDeclaredMethod("start");
        MethodOperation method = new MethodOperation(key,start,modelFactory);
        assertTrue(method.hasIntent());
        assertTrue((Boolean) method.meta().get(Operation.INTENT).get());
    }

    @Test
    public void method_that_has_no_intent_hasIntent_false() {
        MethodOperation method = newMethodOperation("returns_object");
        assertFalse(method.hasIntent());
        assertFalse((Boolean) method.meta().get(Operation.INTENT).get());
    }

    @Test
    public void method_that_returns_object_other_than_intent_returns_it_in_a_model() {
        MethodOperation method = newMethodOperation("returns_object");
        StringConstantModel model = (StringConstantModel) method.invoke();
        assertEquals("Foo!",model.toString());
    }

    ModelFactory modelFactory = new ObjectModelFactory();

    MethodOperation newMethodOperation(String name) {
        for (Method method : SampleObject.class.getDeclaredMethods()) {
            if (method.getName().equals(name)) {
                return new MethodOperation(sample,method,modelFactory);
            }
        }
        throw new IllegalArgumentException(name);
    }

}
