package net.baremodels.device.swing;

import net.baremodels.model.Model;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleAppContext;
import net.baremodels.runner.SyncRunner;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.assertTrue;

public class SwingSyncRunnerTest {

    AppContext appContext = new SimpleAppContext();
    Model.Listener modelListener;
    SwingSyncRunner testObject;

    @Before
    public void init() {
        Mocks.init(this);
        testObject = new SwingSyncRunner(appContext,modelListener);
    }

    @Test
    public void newInstance_returns_SyncRunner() {
        SyncRunner runner = SwingSyncRunner.newInstance(appContext, model-> System.out.println(model));
        assertTrue(runner instanceof SyncRunner);
    }

    @Test
    public void can_create() {
        testObject = new SwingSyncRunner(appContext,modelListener);
    }
}
