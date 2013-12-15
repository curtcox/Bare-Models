package net.baremodels.device.awt;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILabel;
import net.baremodels.ui.UILayout;
import net.miginfocom.swing.MigLayout;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class AwtDeviceTest {

    UILayout layout = new UILayout();
    Container added;
    Frame frame = new Frame() {
        public Component add(Component component) {
            added = (Container) component;
            return null;
        }
    };
    AwtWidgetSupplier supplier = new AwtWidgetSupplier();
    ComponentConstraintSupplier componentConstraintSupplier = new SimpleComponentConstraintSupplier(new MigLayout(),new HashMap<>());
    SimpleComponentTranslator translator = new SimpleComponentTranslator(supplier, componentConstraintSupplier);
    WaitingComponentListener listener = new WaitingComponentListener();

    Intent intent;
    AwtDevice testObject = new AwtDevice(frame,translator,listener,i -> intent = i);

    @Test
    public void can_create() {
        new AwtDevice(frame,translator,listener,null);
    }

    @Test
    public void display_returns_selected_model() {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIContainer container = SimpleUIContainer.of(expected,"name",new UILabel("Foo"));
        listener.onSelected(expected);

        Model actual = testObject.display(container,layout);

        assertSame(expected, actual);
    }

    @Test
    public void display_adds_translated_component() {
        Model expected = ModelFactory.DEFAULT.of("?");
        UIContainer container = SimpleUIContainer.of(expected,"name",new UILabel("Foo"));
        listener.onSelected(expected);

        testObject.display(container,layout);

        assertTrue(added instanceof Panel);
        assertTrue(added.getComponent(0) instanceof Label);
    }

    @Test
    public void onIntent_relays_intent_to_constructor_listener() {
        Intent expected = new Intent(null) {};

        testObject.onIntent(expected);

        assertSame(expected,intent);
    }
}
