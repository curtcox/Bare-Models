package net.baremodels.ui;

import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class UIButtonTest {

    Model model = ModelFactory.DEFAULT.of(new Nucleus());
    String name = "Fred";

    @Test
    public void getName_returns_name_from_constructor() {
        String actual = new UIButton(model,name).getName();
        assertSame(name,actual);
    }

    @Test
    public void getModel_returns_model_from_constructor() {
        Model model = ModelFactory.DEFAULT.of(new Nucleus());
        Model actual = new UIButton(model).getModel();
        assertSame(model,actual);
    }

    @Test
    public void equals() {
        assertEquals(new UIButton(model,"teams"),new UIButton(model,"teams"));
        assertEquals(new UIButton(model,"users"),new UIButton(model,"users"));
    }

    @Test
    public void hashCode_equals() {
        assertEquals(new UIButton(model,"teams").hashCode(),new UIButton(model,"teams").hashCode());
        assertEquals(new UIButton(model,"users").hashCode(),new UIButton(model,"users").hashCode());
    }

    @Test
    public void not_equals() {
        assertNotEquals(new UIButton(model,"teams"), new UIButton(model,"users"));
        assertNotEquals(new UIButton(model,"users"), new UIButton(model,"teams"));
        assertNotEquals(new UIButton(model,"users"), new HashMap());
        assertNotEquals(new UIButton(model,"users"), null);
    }

    private void assertNotEquals(UIButton a, Object b) {
        assertFalse(a.equals(b));
    }

    @Test
    public void toString_returns_button_name() {
        assertEquals("Button(teams)",new UIButton(model,"teams").toString());
    }
}
