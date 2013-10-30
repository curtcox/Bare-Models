package net.baremodels.ui;

import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class SimpleUIContainerTest {

    Model nucleus;
    Model teams;
    Model users;

    @Before
    public void setUp() {
        nucleus = ModelFactory.DEFAULT.of(new Nucleus());
        teams = nucleus.properties().get("teams").model();
        users = nucleus.properties().get("users").model();
    }

    @Test
    public void can_create() {
       assertNotNull(SimpleUIContainer.of(nucleus));
    }

    @Test
    public void is_iterable() {
        for (UIComponent component : SimpleUIContainer.of(nucleus)) ;
    }

    @Test
    public void uses_constructor_args() {
        UIComponent a = new UIButton(teams);
        UIComponent b = new UIButton(users);
        List<UIComponent> list = new ArrayList<>();

        String name = "name";
        SimpleUIContainer container = SimpleUIContainer.of(nucleus, name, a, b);

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
        SimpleUIContainer container = SimpleUIContainer.of(nucleus);
        assertSame(nucleus, container.getModel());
    }

    @Test
    public void uses_constructor_name() {
        String name = "name";
        SimpleUIContainer container = SimpleUIContainer.of(nucleus, name);

        assertSame(name, container.getName());
    }

    @Test
    public void uses_constructor_components() {
        UIComponent a = new UIButton(teams);
        UIComponent b = new UIButton(users);
        List<UIComponent> list = new ArrayList<>();

        String name = "name";
        SimpleUIContainer container = SimpleUIContainer.of(nucleus, name, a, b);

        for (UIComponent component : container) {
            list.add(component);
        }
        assertSame(a, list.get(0));
        assertSame(b, list.get(1));
    }

    @Test
    public void toString_uses_name_and_components() {
        UIComponent a = new UIButton(teams);
        UIComponent b = new UIButton(users);
        List<UIComponent> list = Arrays.asList(a,b);

        String name = "name";
        SimpleUIContainer container = SimpleUIContainer.of(nucleus, name, a, b);

        assertEquals(String.format("%s%s", name, list), container.toString());
    }

}
