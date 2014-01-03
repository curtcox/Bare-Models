package net.baremodels.device.swing.icon;

import net.baremodels.ui.UIGlyph;
import net.baremodels.ui.UIIcon;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class SwingIconSupplierTest {

    SwingIconSupplier testObject = new SwingIconSupplier();

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void getIcon_returns_FontAwesomeIcon_for_glyph() {
        Icon actual = testObject.getIcon(new UIIcon(UIGlyph.android));

        assertTrue(actual instanceof FontAwesomeIcon);
    }

}
