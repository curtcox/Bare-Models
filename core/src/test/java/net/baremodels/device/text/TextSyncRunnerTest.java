package net.baremodels.device.text;

import net.baremodels.intent.Intent;
import net.baremodels.model.Inspectable;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.*;
import org.junit.Test;
import test.models.Car;
import test.models.StartIntent;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TextSyncRunnerTest {

    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model original = modelFactory.of("original");
    Model selected = modelFactory.of("selected");
    FakeUser user = new FakeUser() {
        @Override
        public Model pickModelFrom(TextUiState state) {
            return selected;
        }
    };
    Intent intent;
    AppContext appContext = new SimpleAppContext();
    NextModelGenerator generator = new SelectedNextModelGenerator();
    TextSyncRunner testObject = new TextSyncRunner(appContext,user, generator, x -> {}, i->intent = i);

    @Test
    public void is_a_Runner() {
        assertTrue(new TextSyncRunner(appContext,user, generator, x -> {}, i -> intent = i) instanceof SyncRunner);
    }

    @Test
    public void display_returns_model_picked_by_user() {
        Inspectable returned = testObject.display(original);

        assertSame(selected, returned);
    }

    @Test
    public void display_returns_same_model_when_selected_model_produces_single_intent() {
        Car car = new Car();
        Model model = modelFactory.of(car);
        selected = modelFactory.of(car.key);

        Inspectable returned = testObject.display(model);

        assertSame(model, returned);
    }

    @Test
    public void display_notifies_listener_with_intent_when_selected_model_produces_single_intent() {
        Car car = new Car();
        Model model = modelFactory.of(car);
        selected = modelFactory.of(car.key);

        testObject.display(model);

        assertTrue("intent=" + intent,intent instanceof StartIntent);
        StartIntent startIntent = (StartIntent) intent;
        assertSame(car, startIntent.target);
    }

}
