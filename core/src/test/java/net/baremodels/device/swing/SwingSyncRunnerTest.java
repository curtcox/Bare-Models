package net.baremodels.device.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.model.Model;
import net.baremodels.runner.*;
import net.baremodels.ui.SimpleUIContainer;
import net.baremodels.ui.UIContainer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.assertTrue;
import static test.mock.Mocks._;

public class SwingSyncRunnerTest {

    Model model = NucleusTestFactory.newNucleusModel();
    UIContainer container = SimpleUIContainer.of(model);
    AppContext appContext = new SimpleAppContext();
    ModelContainerRenderer renderer;
    NextModelGenerator generator = new SelectedNextModelGenerator();
    Model.Listener modelListener;
    SwingSyncRunner testObject;

    @Before
    public void init() {
        Mocks.init(this);
        _(container); renderer.render(model);
        testObject = new SwingSyncRunner(appContext,renderer,generator,modelListener);
    }

    @Test
    public void newInstance_returns_SyncRunner() {
        SyncRunner runner = SwingSyncRunner.newInstance(appContext, renderer,generator, model-> System.out.println(model));
        assertTrue(runner instanceof SyncRunner);
    }

    @Test(timeout = 1000)
    public void newInstance_can_set_model() {
        SyncRunner runner = SwingSyncRunner.newInstance(appContext, renderer, generator, model-> System.out.println(model));
        runner.setModel(model,(x) -> true);
    }

// This test will timeout waiting for user input if all is OK.
// If it threw an exception that would be an actual failure.
    @Test(timeout = 1000) @Ignore
    public void newInstance_can_display() {
        SyncRunner runner = SwingSyncRunner.newInstance(appContext, renderer, generator, model-> System.out.println(model));
        runner.display(model);
    }

    @Test
    public void can_create() {
        testObject = new SwingSyncRunner(appContext,renderer,generator,modelListener);
    }

    @Test
    public void can_setModel() {
        testObject = new SwingSyncRunner(appContext,renderer,generator,modelListener);
    }

}
