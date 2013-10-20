package net.baremodels.uat;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.text.FakeUser;
import net.baremodels.text.TextRunner;

import static junit.framework.Assert.assertTrue;

/**
 * A user acceptance test.
 */
public final class UAT {

    final FakeUser user = new FakeUser() {
        @Override
        public Model set(String text) {
            screen = text;
            return null;
        }
    };
    final ModelFactory modelFactory = ModelFactory.DEFAULT;
    final TextRunner runner = new TextRunner(user);

    private String screen;
    private Object showing;

    /**
     * Show an initial object.
     */
    public void show(Object object) {
        showing = object;
    }

    /**
     * Select the given object.
     * This will fail if the given object is not visible.
     */
    public void select(Object object) {
        if (object==showing) {
            return;
        }
        throw new IllegalStateException(String.format("[%s] is not on screen",object));
    }

    /**
     * Assert that the screen contains the given value.
     */
    public void assertScreenContains(String value) {
        Model model = modelFactory.of(showing);
        runner.setModel(model,x->x==model);

        assertTrue(value + " not found in " + screen,screen.contains(value));
    }
}
