package net.baremodels.runner;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertSame;

public class SimpleLayoutSupplierTest {

    Object layoutManager = new Object();
    Map constraints = new HashMap<>();

    SimpleLayoutSupplier testObject = new SimpleLayoutSupplier(layoutManager,constraints);

    @Test
    public void getLayoutManager_uses_constructor_value() {
        assertSame(layoutManager,testObject.getLayoutManager());
    }

    @Test
    public void getComponentConstraints_uses_constructor_value() {
        Object component = new Object();
        Object constraint = toString();
        constraints.put(component,constraint);
        assertSame(constraint,testObject.getComponentConstraints(component));
    }

}
