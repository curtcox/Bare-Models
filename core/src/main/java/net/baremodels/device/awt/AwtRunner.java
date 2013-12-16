package net.baremodels.device.awt;

import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.model.Model;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleSyncRunner;

/**
 * A runner implementation that uses AWT.
 */
public final class AwtRunner
    extends SimpleSyncRunner
{
    public AwtRunner(AppContext appContext, Model.Listener listener) {
        super(appContext, new SimpleModelRenderer(),AwtDevice.newInstance(new DesktopIntentHandler()), listener);
    }
}
