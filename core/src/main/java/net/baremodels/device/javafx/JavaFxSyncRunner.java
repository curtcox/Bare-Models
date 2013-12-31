package net.baremodels.device.javafx;

import net.baremodels.model.Model;
import net.baremodels.model.NavigationContext;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleSyncRunner;

public class JavaFxSyncRunner
    extends SimpleSyncRunner
{
    public JavaFxSyncRunner(AppContext appContext, NavigationContext navigationContext, Model.Listener listener) {
        super(appContext, navigationContext, JavaFxSyncDevice.newInstance(), listener);
    }
}
