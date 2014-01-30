package net.baremodels.device.swing;

import net.miginfocom.swing.MigLayout;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class EDTWrapperTest {

    @Test
    public void wrapper_of_MigLayout_implements_same_interfaces() {
        Object actual = EDTWrapper.of(new MigLayout());
        assertTrue(actual instanceof java.awt.LayoutManager2);
        assertTrue(actual instanceof java.io.Externalizable);
    }

    @Test
    public void wrapper_of_MigLayout_throws_exception_when_off_of_EDT() {
        LayoutManager2 actual = EDTWrapper.of(new MigLayout());
        try {
            actual.invalidateLayout(null);
            fail();
        } catch (IllegalThreadStateException e) {
            String message = e.getMessage();
            assertTrue(message.contains("invalidateLayout"));
            assertTrue(message.contains("is only valid from EDT"));
        }
    }

    @Test
    public void wrapper_of_MigLayout_does_not_throws_exception_when_on_EDT() throws Exception {
        LayoutManager2 actual = EDTWrapper.of(new MigLayout());
        EventQueue.invokeAndWait(()-> actual.invalidateLayout(null));
    }

}
