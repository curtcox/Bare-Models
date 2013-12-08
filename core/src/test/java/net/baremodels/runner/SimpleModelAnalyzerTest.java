package net.baremodels.runner;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import org.junit.Test;
import test.models.Car;
import test.models.Key;
import test.models.Part;
import test.models.Passenger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

public class SimpleModelAnalyzerTest {

    ModelFactory factory = ModelFactory.DEFAULT;
    Car car = new Car();
    Model key = factory.of(new Key(car));
    Model part = factory.of(new Part());
    Model passenger = factory.of(new Passenger("Fred"));
    SimpleModelAnalyzer testObject = new SimpleModelAnalyzer();

    @Test
    public void is_ModelAnalyzer() {
        assertTrue(testObject instanceof ModelAnalyzer);
    }

    @Test
    public void generatesSingleIntent_returns_false_for_models_that_dont() {
        assertFalse(testObject.generatesSingleIntent(factory.of(car)));
        assertFalse(testObject.generatesSingleIntent(part));
        assertFalse(testObject.generatesSingleIntent(passenger));
    }

    @Test
    public void generatesSingleIntent_returns_true_for_models_that_do() {
        assertTrue(testObject.generatesSingleIntent(key));
    }

    @Test
    public void generateIntent_returns_intent_for_model() {
        Intent intent = testObject.generateIntent(key);

        assertSame(car,intent.target);
    }

}
