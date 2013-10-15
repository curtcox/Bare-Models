package net.baremodels.text;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.Runner;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.*;

public class TextRunnerTest {

    FakeUser user;
    Model model = ModelFactory.DEFAULT.of("Some random value");
    Model selected;
    Predicate<Model> listener = new Predicate<Model>() {
        @Override
        public boolean test(Model model) {
            selected = model;
            return false;
        }
    };
    TextRunner testObject = new TextRunner(user);

    @Test
    public void is_a_Runner() {
        assertTrue(new TextRunner(user) instanceof Runner);
    }

    @Test
    public void setModel_notifies_listener_with_model() {
        testObject.setModel(model,listener);

        assertSame(model,selected);
    }
}
