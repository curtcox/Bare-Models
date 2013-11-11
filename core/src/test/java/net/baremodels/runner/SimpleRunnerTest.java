package net.baremodels.runner;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.text.TextDevice;
import org.junit.Test;
import test.models.Car;
import test.models.Key;
import test.models.StartIntent;

import static org.junit.Assert.*;

public class SimpleRunnerTest {

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

    @Test
    public void setModel_notifies_model_listener_on_selection() {
        SimpleRunner testObject = new SimpleRunner(modelRenderer, device,listener);

        testObject.setModel(initial,m -> listener.notified == null );

        assertSame(selected, listener.notified);
        assertTrue(intent == null);
    }

    @Test
    public void setModel_notifies_device_of_intent_when_model_generates_single_intent() {
        Car car = new Car();
        Key key = car.key;
        selected = modelFactory.of(key);

        SimpleRunner testObject = new SimpleRunner(modelRenderer, device,listener);

        testObject.setModel(initial,m -> listener.notified == null );

        assertTrue(intent instanceof StartIntent);
        StartIntent startIntent = (StartIntent) intent;
        assertSame(car, startIntent.target);
    }

}
