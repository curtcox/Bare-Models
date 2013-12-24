package net.baremodels.device.awt;

import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.model.Model;
import net.baremodels.model.NavigationContext;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleSyncRunner;

/**
 * A runner implementation that uses AWT.
 */
public final class AwtSyncRunner
    extends SimpleSyncRunner
{
    public AwtSyncRunner(AppContext appContext, NavigationContext navigationContext, Model.Listener listener) {
        super(appContext, navigationContext, new SimpleModelRenderer(), AwtSyncDevice.newInstance(new DesktopIntentHandler()), listener);
    }
}
