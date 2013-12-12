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
}
