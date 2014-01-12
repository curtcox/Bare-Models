package net.baremodels.device.swing;

import net.baremodels.model.Model;
import net.baremodels.runner.*;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.assertTrue;

public class SwingSyncRunnerTest {

    AppContext appContext = new SimpleAppContext();
    NextModelGenerator generator = new SelectedNextModelGenerator();
    Model.Listener modelListener;
    SwingSyncRunner testObject;

    @Before
    public void init() {
        Mocks.init(this);
        testObject = new SwingSyncRunner(appContext,generator,modelListener);
    }

    @Test
    public void newInstance_returns_SyncRunner() {
        SyncRunner runner = SwingSyncRunner.newInstance(appContext, generator, model-> System.out.println(model));
        assertTrue(runner instanceof SyncRunner);
    }

    @Test
    public void can_create() {
        testObject = new SwingSyncRunner(appContext,generator,modelListener);
    }
}
