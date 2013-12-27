package net.baremodels.device.swing;

import net.baremodels.device.DeviceState;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.SimpleContainerTranslator;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.ui.*;
import net.baremodels.ui.UILayout.*;
import net.miginfocom.swing.MigLayout;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SwingSyncDeviceTest {


    Map<UIComponent, Constraints> componentConstraints = new HashMap<>();
    UILayout layout = new UILayout(componentConstraints);
    Container added;
    JFrame frame = new JFrame() {
        public void setContentPane(Container contentPane) {
            added = contentPane;
        }
    };
    SwingWidgetSupplier supplier = new SwingWidgetSupplier();
    SimpleComponentConstraintSupplier layoutSupplier = new SimpleComponentConstraintSupplier(new MigLayout());
    SimpleContainerTranslator translator = new SimpleContainerTranslator(supplier,layoutSupplier);
    WaitingComponentListener listener = new WaitingComponentListener();

    Intent intent;
    SwingSyncDevice testObject = new SwingSyncDevice(frame,translator,listener,i -> intent = i);

    @Test
    public void can_create() {
        new SwingSyncDevice(frame,translator,listener,null);
    }

    @Test(timeout = 1000)
    public void display_returns_selected_model() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        UIContainer container = SimpleUIContainer.of(expected,component);
        componentConstraints.put(component,new Constraints(""));
        listener.onSelected(expected);

        Model actual = testObject.display(container,layout);

        assertSame(expected, actual);
    }

    @Test(timeout = 1000)
    public void display_adds_translated_component() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        UIContainer container = SimpleUIContainer.of(expected,component);
        componentConstraints.put(component,new Constraints(""));
        listener.onSelected(expected);

        testObject.display(container,layout);

        waitForIt();

        assertTrue("added = " + added,added instanceof JPanel);
        assertTrue(added.getComponent(0) instanceof JLabel);
    }

    @Test(timeout = 1000)
    public void display_throws_IllegalArgumentException_when_missing_layout_for_component() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        UIContainer container = SimpleUIContainer.of(expected,component);

        try {
            testObject.display(container,layout);
            fail();
        } catch (IllegalArgumentException e) {
            String message = String.format("Missing constraints for %s", component);
            assertEquals(message,e.getMessage());
        }
    }

    @Test(timeout = 1000)
    public void redisplay_adds_translated_component_when_called_from_EDT() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        UIContainer container = SimpleUIContainer.of(expected,component);
        listener.onSelected(expected);
        componentConstraints.put(component,new Constraints(""));

        EventQueue.invokeAndWait(() -> testObject.redisplay(container, layout));

        assertTrue("added = " + added,added instanceof JPanel);
        assertTrue(added.getComponent(0) instanceof JLabel);
    }

    @Test(timeout = 1000)
    public void redisplay_throws_exception_when_not_called_from_EDT() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIContainer container = SimpleUIContainer.of(expected);

        try {
            testObject.redisplay(container,layout);
            fail();
        } catch (IllegalThreadStateException e) {
            String message = "Must only be called from the EDT.";
            assertEquals(message,e.getMessage());
        }
    }

    @Test(timeout = 1000)
    public void redisplay_throws_exception_when_missingLayout_for_component() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        UIContainer container = SimpleUIContainer.of(expected,component);

        try {
            EventQueue.invokeAndWait(() -> testObject.redisplay(container, layout));
            fail();
        } catch (InvocationTargetException e) {
            IllegalArgumentException cause = (IllegalArgumentException) e.getCause();
            String message = String.format("Missing constraints for %s", component);
            assertEquals(message, cause.getMessage());
        }
    }

    private void waitForIt() throws Exception {
        EventQueue.invokeAndWait(() -> {});
    }

    @Test(timeout = 1000)
    public void onIntent_relays_intent_to_constructor_handler() {
        Intent expected = new Intent(null) {};

        testObject.onIntent(expected);

        assertSame(expected,intent);
    }

    @Test(timeout = 1000)
    public void getDeviceState_returns_size_from_frame() {

        DeviceState deviceState = testObject.getDeviceState();

        assertEquals(frame.getSize().width,  deviceState.width);
        assertEquals(frame.getSize().height, deviceState.height);
    }
}
