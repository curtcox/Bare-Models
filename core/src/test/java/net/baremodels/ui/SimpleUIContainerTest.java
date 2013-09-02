package net.baremodels.ui;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;

public class SimpleUIContainerTest {

    @Test
    public void can_create() {
       new SimpleUIContainer();
    }

    @Test
    public void is_iterable() {
        for (UIComponent component : new SimpleUIContainer()) ;
    }

    @Test
    public void uses_constructor_args() {
        UIComponent a = new UIButton("a");
        UIComponent b = new UIButton("b");
        List<UIComponent> list = new ArrayList<>();

        for (UIComponent component : new SimpleUIContainer(a,b)) {
            list.add(component);
        }

        assertSame(a, list.get(0));
        assertSame(b,list.get(1));

    }
}
