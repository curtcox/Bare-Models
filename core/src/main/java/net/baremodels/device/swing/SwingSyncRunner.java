package net.baremodels.device.swing;

import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.model.Model;
import net.baremodels.model.NavigationContext;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleSyncRunner;

public final class SwingSyncRunner
    extends SimpleSyncRunner
{
    public SwingSyncRunner(AppContext appContext, NavigationContext navigationContext, Model.Listener listener) {
        super(appContext, navigationContext, new SimpleModelRenderer(), SwingSyncDevice.newInstance(new DesktopIntentHandler()), listener);
    }
}