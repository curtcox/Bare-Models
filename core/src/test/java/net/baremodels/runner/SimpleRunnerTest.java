package net.baremodels.runner;

import net.baremodels.device.GenericDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;
import net.baremodels.ui.UIComponent;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class SimpleRunnerTest {

    ModelRenderer modelRenderer = new SimpleModelRenderer();

    GenericDevice driver = new GenericDevice() {
        @Override
        public Model display(UIComponent ui) {
            return selected;
        }

        @Override
        public void onIntent(Intent intent) {
        }
    };

    Listener listener = new Listener();
    class Listener implements Model.Listener {
        Model notified;
        @Override
        public void onChange(Model model) {
            notified = model;
        }
    };

    Model initial = ObjectModel.of("initial");
    Model selected = ObjectModel.of("selected");

    SimpleRunner testObject = new SimpleRunner(modelRenderer,driver,listener);

    @Test
    public void model_notifies_listener_on_selection() {

        testObject.setModel(initial,m -> listener.notified == null );

        assertSame(selected, listener.notified);
    }

}
