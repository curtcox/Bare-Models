package net.baremodels.runner;

import net.baremodels.ui.UILayout.Constraints;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class SimpleComponentConstraintSupplierTest {

    Object layoutManager = new Object();

    SimpleComponentConstraintSupplier testObject = new SimpleComponentConstraintSupplier(layoutManager);

    @Test
    public void getLayoutManager_uses_constructor_value() {
        assertSame(layoutManager,testObject.getLayoutManager());
    }

    @Test
    public void getComponentConstraints_uses_constructor_value() {
        String expected = toString();
        Constraints component = new Constraints(expected);
        assertSame(expected, testObject.getComponentConstraints(component));
    }

}
