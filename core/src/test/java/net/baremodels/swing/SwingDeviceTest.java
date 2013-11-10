package net.baremodels.swing;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleComponentListener;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UILabel;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SwingDeviceTest {

    Container added;
    JFrame frame = new JFrame() {
        public void setContentPane(Container contentPane) {
            added = contentPane;
        }
    };
    SwingWidgetSupplier supplier = new SwingWidgetSupplier();
    SimpleComponentTranslator translator = new SimpleComponentTranslator(supplier);
    SimpleComponentListener listener = new SimpleComponentListener();

    SwingDevice testObject = new SwingDevice(frame,translator,listener);

    @Test
    public void can_create() {
        new SwingDevice(frame,translator,listener);
    }

    @Test
    public void display_returns_selected_model() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        listener.selected = expected;

        Model actual = testObject.display(component);

        assertSame(expected, actual);
    }

    @Test
    public void display_adds_translated_component() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        listener.selected = expected;

        testObject.display(component);

        waitForIt();

        assertTrue("added = " + added,added instanceof JLabel);
    }

    private void waitForIt() throws Exception {
        EventQueue.invokeAndWait(() -> {});
    }

}
