package net.baremodels.device.swing;

import net.baremodels.device.DeviceState;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleContainerTranslator;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.ui.*;
import net.miginfocom.swing.MigLayout;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SwingSyncDeviceTest {

    UILayout layout = new UILayout(new HashMap<>());
    Container added;
    JFrame frame = new JFrame() {
        public void setContentPane(Container contentPane) {
            added = contentPane;
        }
    };
    SwingWidgetSupplier supplier = new SwingWidgetSupplier();
    SimpleComponentConstraintSupplier layoutSupplier = new SimpleComponentConstraintSupplier(new MigLayout(), new HashMap<>());
    SimpleContainerTranslator translator = new SimpleContainerTranslator(supplier,layoutSupplier);
    WaitingComponentListener listener = new WaitingComponentListener();

    Intent intent;
    SwingSyncDevice testObject = new SwingSyncDevice(frame,translator,listener,i -> intent = i);

    @Test
    public void can_create() {
        new SwingSyncDevice(frame,translator,listener,null);
    }

    @Test
    public void display_returns_selected_model() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        UIContainer container = SimpleUIContainer.of(expected,component);
        listener.onSelected(expected);

        Model actual = testObject.display(container,layout);

        assertSame(expected, actual);
    }

    @Test
    public void display_adds_translated_component() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        UIContainer container = SimpleUIContainer.of(expected,component);
        listener.onSelected(expected);

        testObject.display(container,layout);

        waitForIt();

        assertTrue("added = " + added,added instanceof JPanel);
        assertTrue(added.getComponent(0) instanceof JLabel);
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

    @Test
    public void getDeviceState_returns_size_from_frame() {

        DeviceState deviceState = testObject.getDeviceState();

        assertEquals(frame.getSize().width,  deviceState.width);
        assertEquals(frame.getSize().height, deviceState.height);
    }
}
