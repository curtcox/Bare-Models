package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;
import net.baremodels.text.TextDevice;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class SimpleRunnerTest {

    Model initial = ObjectModel.of("initial");
    Model selected = ObjectModel.of("selected");

    ModelRenderer modelRenderer = new SimpleModelRenderer();

    TextDevice driver = new TextDevice(t -> selected);

    Listener listener = new Listener();
    class Listener implements Model.Listener {
        Model notified;
        @Override
        public void onChange(Model model) {
            notified = model;
        }
    };


    SimpleRunner testObject = new SimpleRunner(modelRenderer,driver,listener);

    @Test
    public void model_notifies_listener_on_selection() {

        testObject.setModel(initial,m -> listener.notified == null );

        assertSame(selected, listener.notified);
    }

}
