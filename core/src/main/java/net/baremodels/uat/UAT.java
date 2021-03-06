package net.baremodels.uat;

import net.baremodels.device.text.FakeUser;
import net.baremodels.device.text.TextSyncRunner;
import net.baremodels.device.text.TextUiState;
import net.baremodels.intent.Intent;
import net.baremodels.model.Inspectable;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.NextModelGenerator;
import net.baremodels.runner.SelectedNextModelGenerator;
import net.baremodels.runner.SimpleAppContext;
import net.baremodels.ui.UIGlyph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A user acceptance test.
 * Usage of this class will generally be in unit tests.
 */
public final class UAT {

    private volatile Model choice;
    private final FakeUser user = new FakeUser() {
        @Override
        public Model pickModelFrom(TextUiState state) {
            Model selected = choice==null ? modelFactory.of(null) : choice;
            UAT.this.state = state.withSelected(selected);
            return selected;
        }
    };

    TextUiState state;
    private Object showing;
    private Model showingModel;

    private final ModelFactory modelFactory;
    private final LinkedList<Intent> intents = new LinkedList<>();
    private final TextSyncRunner runner;
    private final AssertionListener listener;

    /**
     * Return a UAT that fails assertions the same way JUnit does.
     */
    public UAT() {
        this(new SimpleAppContext(),new SelectedNextModelGenerator());
    }

    /**
     * Return a UAT that fails assertions the same way JUnit does.
     */
    public UAT(AppContext appContext, NextModelGenerator generator) {
       this(appContext, generator, (failure)-> {throw new AssertionError(failure.message);}, ModelFactory.DEFAULT );
    }

    UAT(AppContext appContext, NextModelGenerator generator, AssertionListener listener, ModelFactory modelFactory) {
        this.listener = listener;
        this.modelFactory = modelFactory;
        runner = new TextSyncRunner(appContext,user,generator, x -> showingModel = x, i-> { intents.add(i); return null;});
    }

    /**
     * Show an initial object.
     * In other words, make the given object the subject of the display.
     */
    public void show(Object object) {
        showing = object;
        showingModel = modelFactory.of(showing);
        Inspectable selected = runner.display(showingModel);
        showingModel = runner.generateNextModel(showingModel,selected);
        state = state.withNext(showingModel);
    }

    /**
     * Select the given object.
     * This will fail if the given object is not visible.
     */
    public void select(Object object) {
        verifyShowing();
        verifySelectable(object);
        choice = modelFactory.of(object);
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
        showingModel = (Model) runner.display(showingModel);
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
     * Assert that the screen contains the given glyph.
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
     * Assert that the screen contains the given glyphs.
     */
    public void assertScreenContains(UIGlyph... glyphs) {
        verifyShowing();
        List<UIGlyph> missing = new ArrayList<>(Arrays.asList(glyphs));
        for (UIGlyph value : glyphs) {
            if (screenContains(value.name())) {
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
