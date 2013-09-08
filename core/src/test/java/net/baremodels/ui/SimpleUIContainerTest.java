package net.baremodels.ui;

import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;

public class SimpleUIContainerTest {

    Model nucleus;
    Model teams;
    Model users;

    @Before
    public void setUp() {
        nucleus = ObjectModel.of(new Nucleus());
        teams = nucleus.properties().get("teams").model();
        users = nucleus.properties().get("users").model();
    }

    @Test
    public void can_create() {
       new SimpleUIContainer(nucleus);
    }

    @Test
    public void is_iterable() {
        for (UIComponent component : new SimpleUIContainer(nucleus)) ;
    }

    @Test
    public void uses_constructor_args() {
        UIComponent a = new UIButton(teams);
        UIComponent b = new UIButton(users);
        List<UIComponent> list = new ArrayList<>();

        String name = "name";
        SimpleUIContainer container = new SimpleUIContainer(nucleus, name, a, b);

        assertSame(nucleus, container.getModel());
        assertSame(name, container.getName());
        for (UIComponent component : container) {
            list.add(component);
        }
        assertSame(a, list.get(0));
        assertSame(b, list.get(1));
    }

    @Test
    public void uses_constructor_model() {
        SimpleUIContainer container = new SimpleUIContainer(nucleus);
        assertSame(nucleus, container.getModel());
    }

    @Test
    public void uses_constructor_name() {
        String name = "name";
        SimpleUIContainer container = new SimpleUIContainer(nucleus, name);

        assertSame(name, container.getName());
    }

    @Test
    public void uses_constructor_components() {
        UIComponent a = new UIButton(teams);
        UIComponent b = new UIButton(users);
        List<UIComponent> list = new ArrayList<>();

        String name = "name";
        SimpleUIContainer container = new SimpleUIContainer(nucleus, name, a, b);

        for (UIComponent component : container) {
            list.add(component);
        }
        assertSame(a, list.get(0));
        assertSame(b, list.get(1));
    }

}
