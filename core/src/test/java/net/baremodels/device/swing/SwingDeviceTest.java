package net.baremodels.device.swing;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UILabel;
import net.miginfocom.swing.MigLayout;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

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
    SimpleComponentConstraintSupplier layoutSupplier = new SimpleComponentConstraintSupplier(new MigLayout(), new HashMap<>());
    SimpleComponentTranslator translator = new SimpleComponentTranslator(supplier,layoutSupplier);
    WaitingComponentListener listener = new WaitingComponentListener();

    Intent intent;
    SwingDevice testObject = new SwingDevice(frame,translator,listener,i -> intent = i);

    @Test
    public void can_create() {
        new SwingDevice(frame,translator,listener,null);
    }

    @Test
    public void display_returns_selected_model() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        listener.onSelected(expected);

        Model actual = testObject.display(component);

        assertSame(expected, actual);
    }

    @Test
    public void display_adds_translated_component() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        listener.onSelected(expected);

        testObject.display(component);

        waitForIt();

        assertTrue("added = " + added,added instanceof JLabel);
    }

    private void waitForIt() throws Exception {
        EventQueue.invokeAndWait(() -> {});
    }

    @Test
    public void onIntent_relays_intent_to_constructor_handler() {
        Intent expected = new Intent(null) {};

        testObject.onIntent(expected);

        assertSame(expected,intent);
    }

}
