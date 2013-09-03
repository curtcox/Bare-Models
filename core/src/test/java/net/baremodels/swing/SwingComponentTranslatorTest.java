package net.baremodels.swing;

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
