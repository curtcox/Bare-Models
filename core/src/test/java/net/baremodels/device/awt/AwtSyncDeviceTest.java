package net.baremodels.device.awt;

import net.baremodels.device.DeviceState;
import net.baremodels.intent.Intent;
import net.baremodels.model.Inspectable;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.SimpleContainerTranslator;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.ui.*;
import net.miginfocom.swing.MigLayout;
import org.junit.Test;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class AwtSyncDeviceTest {

    Map<UIComponent, UILayout.Constraints> componentConstraints = new HashMap<>();
    UILayout layout = new UILayout(componentConstraints);
    Container added;
    Frame frame = new Frame() {
        public Component add(Component component) {
            added = (Container) component;
            return null;
        }
    };
    AwtWidgetSupplier supplier = new AwtWidgetSupplier();
    ComponentConstraintSupplier componentConstraintSupplier = new SimpleComponentConstraintSupplier(new MigLayout());
    SimpleContainerTranslator translator = new SimpleContainerTranslator(supplier, componentConstraintSupplier);
    WaitingComponentListener listener = new WaitingComponentListener();

    Intent intent;
    AwtSyncDevice testObject = new AwtSyncDevice(frame,translator,listener,i -> intent = i);

    @Test
    public void can_create() {
        new AwtSyncDevice(frame,translator,listener,null);
    }

    @Test(timeout = 1000)
    public void display_returns_selected_model() {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        UIContainer container = SimpleUIContainer.of(expected,component);
        componentConstraints.put(component,new UILayout.Constraints(""));
        listener.onSelected(expected);

        Inspectable actual = testObject.display(container, layout);

        assertSame(expected, actual);
    }

    @Test(timeout = 1000)
    public void display_adds_translated_component() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UILabel component = new UILabel("Foo");
        UIContainer container = SimpleUIContainer.of(expected,component);
        componentConstraints.put(component,new UILayout.Constraints(""));
        listener.onSelected(expected);

        testObject.display(container,layout);

        waitForIt();

        assertTrue("added = " + added,added instanceof Panel);
        assertTrue(added.getComponent(0) instanceof Label);
    }

    @Test(timeout = 1000)
    public void redisplay_adds_translated_component_when_called_from_EDT() throws Exception {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        UIContainer container = SimpleUIContainer.of(expected,component);
        listener.onSelected(expected);
        componentConstraints.put(component,new UILayout.Constraints(""));

        EventQueue.invokeAndWait(() -> testObject.redisplay(container, layout));

        assertTrue("added = " + added,added instanceof Panel);
        assertTrue(added.getComponent(0) instanceof Label);
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
    public void onIntent_relays_intent_to_constructor_listener() {
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
