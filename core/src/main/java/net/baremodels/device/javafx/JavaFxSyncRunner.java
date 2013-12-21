package net.baremodels.device.javafx;

import net.baremodels.model.Model;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleSyncRunner;

public class JavaFxSyncRunner
    extends SimpleSyncRunner
{
    public JavaFxSyncRunner(AppContext appContext, Model.Listener listener) {
        super(appContext, new SimpleModelRenderer(), JavaFxSyncDevice.newInstance(), listener);
    }
}