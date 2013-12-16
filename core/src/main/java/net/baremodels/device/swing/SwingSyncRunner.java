package net.baremodels.device.swing;

import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.model.Model;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleSyncRunner;

public final class SwingSyncRunner
    extends SimpleSyncRunner
{
    public SwingSyncRunner(AppContext appContext, Model.Listener listener) {
        super(appContext, new SimpleModelRenderer(), SwingSyncDevice.newInstance(new DesktopIntentHandler()), listener);
    }
}