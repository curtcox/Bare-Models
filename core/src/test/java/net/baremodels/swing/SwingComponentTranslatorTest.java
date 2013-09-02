package net.baremodels.swing;

import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertTrue;

public class SwingComponentTranslatorTest {

    SwingComponentTranslator testObject = new SwingComponentTranslator();

    @Test
    public void button() {
        test(new UIButton(),JButton.class);
    }

    private void test(UIComponent ui, Class clazz) {
        assertTrue(clazz.isInstance(testObject.translate(ui)));
    }
}
