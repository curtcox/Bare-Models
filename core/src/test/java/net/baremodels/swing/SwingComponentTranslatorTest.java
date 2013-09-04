package net.baremodels.swing;

import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SwingComponentTranslatorTest {

    SwingComponentTranslator testObject = new SwingComponentTranslator();

    @Test
    public void button() {
        test(new UIButton("a"),JButton.class,"a");
    }

    @Test
    public void container_with_two_buttons() {
        UIComponent one = new UIButton("one");
        UIComponent two = new UIButton("two");
        UIComponent ui = new SimpleUIContainer("c",one,two);

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
        JButton button = (JButton) testObject.translate(new UIButton("a"));
        assertEquals("a",button.getText());
    }

    private void test(UIComponent ui, Class clazz, String name) {
        JComponent component = testObject.translate(ui);
        assertTrue(clazz.isInstance(component));
        assertEquals(name,component.getName());
    }
}
