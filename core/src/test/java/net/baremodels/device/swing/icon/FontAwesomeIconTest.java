package net.baremodels.device.swing.icon;

import net.baremodels.ui.UIGlyph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FontAwesomeIconTest {

    @Test
    public void can_create() {
        new FontAwesomeIcon(UIGlyph.ambulance,16);
    }

    @Test
    public void getIconHeight_returns_size_from_constructor(){
        int size = 13;
        assertEquals(size,new FontAwesomeIcon(UIGlyph.android,size).getIconHeight());
    }

    @Test
    public void getIconWidth_returns_size_from_constructor(){
        int size = 123;
        assertEquals(size,new FontAwesomeIcon(UIGlyph.android,size).getIconWidth());
    }

}
