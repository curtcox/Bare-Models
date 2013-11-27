package net.baremodels.runner;

import net.baremodels.device.AsyncDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.ui.UIComponent;
import org.junit.Test;
import test.models.Car;
import test.models.Key;
import test.models.StartIntent;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class AsyncRunnerTest {

    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model initial = modelFactory.of("initial");
    Model selected = modelFactory.of("selected");

    ModelRenderer modelRenderer = new SimpleModelRenderer();

    Intent intent;
    UIComponent displayed;
    AsyncDevice device = new AsyncDevice() {
        @Override public void display(UIComponent ui) { displayed = ui; }
        @Override public void onIntent(Intent i) { intent = i; }
    };

    AsyncRunner testObject = new AsyncRunner(modelRenderer, device);

    @Test
    public void onSelected_displays_on_device_when_changed_selection() {
        testObject.display(initial);
        testObject.onSelected(selected);

        assertTrue(intent == null);
    }

    @Test
    public void display_does_not_notify_model_listener_on_unchanged_selection() {
        selected = initial;

        testObject.display(initial);

        assertTrue(intent == null);
    }

    @Test
    public void display_displays_rendered_model_on_device() {
        selected = initial;

        testObject.display(initial);

        assertTrue(intent == null);
    }

    @Test
    public void onSelected_notifies_device_of_intent_when_model_generates_single_intent() {
        Car car = new Car();
        Key key = car.key;
        selected = modelFactory.of(key);

        testObject.onSelected(selected);

        assertTrue(intent instanceof StartIntent);
        StartIntent startIntent = (StartIntent) intent;
        assertSame(car, startIntent.target);
    }

}
