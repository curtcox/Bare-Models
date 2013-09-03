package net.baremodels.awt;

import net.baremodels.ui.UIButton;
import net.baremodels.ui.UIComponent;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

public class AwtComponentTranslatorTest {

    AwtComponentTranslator testObject = new AwtComponentTranslator();

    @Test
    public void button() {
        test(new UIButton(""),Button.class);
    }

    private void test(UIComponent ui, Class clazz) {
        assertTrue(clazz.isInstance(testObject.translate(ui)));
    }
}
