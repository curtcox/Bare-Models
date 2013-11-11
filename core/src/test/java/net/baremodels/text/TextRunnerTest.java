package net.baremodels.text;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.Runner;
import org.junit.Test;
import test.models.Car;

import java.util.function.Predicate;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TextRunnerTest {

    FakeUser user;
    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model model = modelFactory.of("Some random value");
    Model selected;
    Predicate<Model> listener = new Predicate<Model>() {
        @Override
        public boolean test(Model model) {
            selected = model;
            return false;
        }
    };
    Intent intent;
    TextRunner testObject = new TextRunner(user,i->intent = i);

    @Test
    public void is_a_Runner() {
        assertTrue(new TextRunner(user,i->intent = i) instanceof Runner);
    }

    @Test
    public void setModel_notifies_listener_with_model() {
        testObject.setModel(model,listener);

        assertSame(model,selected);
    }

    @Test
    public void setModel_notifies_listener_with_intent_when_model_produces_single_intent() {
        Car car = new Car();
        Model model = modelFactory.of(car.key);
        testObject.setModel(model,listener);

        assertSame(model,selected);
    }

}
