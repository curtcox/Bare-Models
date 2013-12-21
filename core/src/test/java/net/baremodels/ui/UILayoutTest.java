package net.baremodels.ui;

import net.baremodels.ui.UILayout.Constraints;
import org.junit.Test;
import test.mock.Mocks;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertSame;

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
        Constraints expected = new Constraints();
        componentConstraints.put(button, expected);
        Constraints actual = testObject.getConstraints(button);

        assertSame(expected,actual);
    }
}
