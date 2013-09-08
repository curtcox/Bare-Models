package net.baremodels.swing;

import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SwingComponentTranslatorTest {

    Model model = ObjectModel.of(new Nucleus());
    Model teams = model.properties().get("teams").model();
    Model users = model.properties().get("users").model();
    UIComponent.Listener listener = null;

    SwingComponentTranslator testObject = new SwingComponentTranslator();

    @Test
    public void button() {
        test(new UIButton(teams,"a"),JButton.class,"a");
    }

    @Test
    public void container_with_two_buttons() {
        UIComponent one = new UIButton(teams,"one");
        UIComponent two = new UIButton(users,"two");
        UIComponent ui = new SimpleUIContainer(model,"c",one,two);

        JComponent actual = testObject.translate(ui);

        assertEquals(2,actual.getComponentCount());
        assertEquals("c",actual.getName());

        JButton buttonOne = (JButton) actual.getComponent(0);
        assertEquals("one",buttonOne.getName());
        assertEquals("one",buttonOne.getText());

        JButton buttonTwo = (JButton) actual.getComponent(1);
        assertEquals("two",buttonTwo.getName());
        assertEquals("two",buttonTwo.getText());
    }

    @Test
    public void button_text() {
        JButton button = (JButton) testObject.translate(new UIButton(teams,"a"));
        assertEquals("a",button.getText());
    }

    private void test(UIComponent ui, Class clazz, String name) {
        JComponent component = testObject.translate(ui);
        assertTrue(clazz.isInstance(component));
        assertEquals(name,component.getName());
    }
}
