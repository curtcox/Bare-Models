package net.baremodels.device.awt;

import net.baremodels.device.swing.SwingSyncRunner;
import net.baremodels.model.Model;
import net.baremodels.runner.*;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.assertTrue;

public class AwtSyncRunnerTest {

    AppContext appContext = new SimpleAppContext();
    NextModelGenerator generator = new SelectedNextModelGenerator();
    Model.Listener modelListener;
    ModelContainerRenderer renderer;
    AwtSyncRunner testObject;

    @Before
    public void init() {
        Mocks.init(this);
        testObject = new AwtSyncRunner(appContext,generator,modelListener);
    }

    @Test
    public void newInstance_returns_SyncRunner() {
        SyncRunner runner = SwingSyncRunner.newInstance(appContext,renderer,generator,model-> System.out.println(model));
        assertTrue(runner instanceof SyncRunner);
    }

    @Test
    public void can_create() {
        testObject = new AwtSyncRunner(appContext,generator,modelListener);
    }
}
