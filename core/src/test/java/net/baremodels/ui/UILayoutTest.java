package net.baremodels.ui;

import net.baremodels.ui.UILayout.Constraints;
import org.junit.Test;
import test.mock.Mocks;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class UILayoutTest {

    UIComponent button;
    Map<UIComponent,Constraints> componentConstraints = new HashMap<>();

    UILayout testObject = new UILayout(componentConstraints);

    @Test
    public void init() {
        Mocks.init(this);
    }

    @Test
    public void getConstraints_gets_constraints_from_constructor() {
        Constraints expected = new Constraints("");
        componentConstraints.put(button, expected);
        Constraints actual = testObject.getConstraints(button);

        assertSame(expected,actual);
    }

    @Test
    public void constraints_value_is_value_from_constructor() {
        String expected = "gsyhgyhag";
        assertSame(expected,new Constraints(expected).value);
    }

    @Test
    public void constraints_equals_when_values_equal() {
        String value = toString();
        assertEquals(new Constraints(value), new Constraints(value));
    }

    @Test
    public void constraints_not_equals_when_values_not_equal() {
        assertFalse(new Constraints("value").equals(new Constraints("other")));
    }

    @Test
    public void constraints_hashCode_when_values_equal() {
        String value = toString();
        assertEquals(new Constraints(value).hashCode(), new Constraints(value).hashCode());
    }

}
