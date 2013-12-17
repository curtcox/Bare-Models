package net.baremodels.runner;

import net.baremodels.ui.UILayout;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertSame;

public class SimpleComponentConstraintSupplierTest {

    Object layoutManager = new Object();
    Map constraints = new HashMap<>();

    SimpleComponentConstraintSupplier testObject = new SimpleComponentConstraintSupplier(layoutManager,constraints);

    @Test
    public void getLayoutManager_uses_constructor_value() {
        assertSame(layoutManager,testObject.getLayoutManager());
    }

    @Test
    public void getComponentConstraints_uses_constructor_value() {
        UILayout.Constraints component = new UILayout.Constraints();
        String constraint = toString();
        constraints.put(component,constraint);
        assertSame(constraint, testObject.getComponentConstraints(component));
    }

}
