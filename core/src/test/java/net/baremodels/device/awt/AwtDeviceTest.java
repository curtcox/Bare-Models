package net.baremodels.device.awt;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleComponentListener;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UILabel;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class AwtDeviceTest {


    Component added;
    Frame frame = new Frame() {
        public Component add(Component component) {
            added = component;
            return null;
        }
    };
    AwtWidgetSupplier supplier = new AwtWidgetSupplier();
    SimpleComponentTranslator translator = new SimpleComponentTranslator(supplier);
    SimpleComponentListener listener = new SimpleComponentListener();

    AwtDevice testObject = new AwtDevice(frame,translator,listener);

    @Test
    public void can_create() {
        new AwtDevice(frame,translator,listener);
    }

    @Test
    public void display_returns_selected_model() {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        listener.onSelected(expected);

        Model actual = testObject.display(component);

        assertSame(expected, actual);
    }

    @Test
    public void display_adds_translated_component() {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIComponent component = new UILabel("Foo");
        listener.onSelected(expected);

        testObject.display(component);

        assertTrue(added instanceof Label);
    }

}
