package net.baremodels.uat;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.text.FakeUser;
import net.baremodels.text.TextRunner;
import net.baremodels.text.TextUiState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A user acceptance test.
 * Usage of this class will generally be in unit tests.
 */
public final class UAT {

    private Model choice;
    private final FakeUser user = new FakeUser() {
        @Override
        public Model pickModelFrom(TextUiState state) {
        UAT.this.state = state;
        return choice;
        }
    };

    private final ModelFactory modelFactory;
    private final LinkedList<Intent> intents = new LinkedList<>();
    private final TextRunner runner = new TextRunner(user,i->intents.add(i));
    private final AssertionListener listener;

    TextUiState state;
    private Object showing;
    private Model showingModel;

    /**
     * Return a UAT that fails assertions the same way JUnit does.
     */
    public UAT() {
       this((failure)-> {throw new AssertionError(failure.message);}, ModelFactory.DEFAULT );
    }

    UAT(AssertionListener listener, ModelFactory modelFactory) {
        this.listener = listener;
        this.modelFactory = modelFactory;
        choice = modelFactory.of(null);
    }

    /**
     * Show an initial object.
     */
    public void show(Object object) {
        showing = object;
        showingModel = modelFactory.of(showing);
        showingModel = runner.display(showingModel);
    }

    /**
     * Select the given object.
     * This will fail if the given object is not visible.
     */
    public void select(Object object) {
        verifyShowing();
        verifySelectable(object);
        show(object);
    }

    private void verifySelectable(Object object) {
        if (!isSelectable(object)) {
            String message = String.format("[%s] is not on screen [%s]", object, state);
            listener.onFailedAssertion(new FailedAssertion(message, state));
            throw new AssertionError(message);
        }
    }

    /**
     * Execute the given object's operation.
     * This will only work if the object is visible and has a corresponding operation.
     */
    public Intent execute(Object object) {
        verifyShowing();
        verifySelectable(object);
        verifyOperation(object);
        choice = modelFactory.of(object);
        showingModel = runner.display(showingModel);
        return intents.getLast();
    }

    private void verifyOperation(Object object) {
        Model model = modelFactory.of(object);
        if (model.operations().isEmpty()) {
            assertFailure(String.format("[%s] does not have an operation to select [%s]", object, state));
        }
    }

    private void assertFailure(String message) {
        listener.onFailedAssertion(new FailedAssertion(message, state));
        throw new AssertionError(message);
    }

    private boolean isSelectable(Object object) {
        Model objectModel = modelFactory.of(object);
        for (Model model : state.selectable) {
            if (model.equals(objectModel)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Assert that the screen contains the given value.
     */
    public void assertScreenContains(String... values) {
        verifyShowing();
        List<String> missing = new ArrayList<>(Arrays.asList(values));
        for (String value : values) {
            if (screenContains(value)) {
                missing.remove(value);
            }
        }
        if (!missing.isEmpty()) {
            assertFailure(missing + " not found in " + state);
        }
    }

    /**
     * Return true if the screen contains all of the given values.
     */
    public boolean screenContains(String... values) {
        verifyShowing();
        for (String value : values) {
            if (!state.text.contains(value)) {
                return false;
            }
        }
        return true;
    }

    private void verifyShowing() {
        if (showing==null) {
            throw new IllegalStateException("This method is only valid after showing an object");
        }
    }
}
