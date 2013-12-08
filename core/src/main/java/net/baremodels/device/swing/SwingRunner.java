package net.baremodels.device.swing;

import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.model.Model;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleSyncRunner;

public final class SwingRunner
    extends SimpleSyncRunner
{
    public SwingRunner(Model.Listener listener) {
        super(new SimpleModelRenderer(), SwingDevice.newInstance(new DesktopIntentHandler()), listener);
    }
}