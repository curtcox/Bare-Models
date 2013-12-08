package net.baremodels.runner;

import net.baremodels.common.User;
import net.baremodels.device.text.TextDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import org.junit.Test;
import test.models.Car;
import test.models.Key;
import test.models.StartIntent;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SimpleSyncRunner_Integration_Test {

    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model initial = modelFactory.of("initial");
    Model selected = modelFactory.of("selected");

    ModelRenderer modelRenderer = new SimpleModelRenderer();

    Intent intent;
    TextDevice device = new TextDevice(t -> selected, i-> intent = i);

    Listener listener = new Listener();
    class Listener implements Model.Listener {
        Model notified;
        @Override
        public void onChange(Model model) {
            notified = model;
        }
    };

    SimpleSyncRunner testObject = new SimpleSyncRunner(modelRenderer, device,listener);

    @Test
    public void display_notifies_model_listener_on_changed_selection() {
        testObject.display(initial);

        assertSame(selected, listener.notified);
        assertTrue(intent == null);
    }

    @Test
    public void display_does_not_notify_model_listener_on_unchanged_selection() {
        selected = initial;

        testObject.display(initial);

        assertSame(null, listener.notified);
        assertTrue(intent == null);
    }

    @Test
    public void display_notifies_device_of_intent_when_model_generates_single_intent() {
        Car car = new Car();
        Key key = car.key;
        selected = modelFactory.of(key);

        testObject.display(initial);

        assertTrue(intent instanceof StartIntent);
        StartIntent startIntent = (StartIntent) intent;
        assertSame(car, startIntent.target);
    }

    @Test
    public void display_returns_given_model_when_selected_model_generates_single_intent() {
        Car car = new Car();
        Key key = car.key;
        selected = modelFactory.of(key);

        Model returned = testObject.display(initial);

        assertSame(initial, returned);
    }

    @Test
    public void display_returns_selected_model_when_it_does_not_generate_intent() {
        Model returned = testObject.display(initial);

        assertSame(selected, returned);
    }

    @Test
    public void display_returns_selected_model_when_it_has_one_operation_that_does_not_generate_intent() {
        SimpleSyncRunner testObject = new SimpleSyncRunner(modelRenderer, device,listener);
        selected = modelFactory.of(new User());
        Model returned = testObject.display(initial);

        assertSame(selected, returned);
    }

}
