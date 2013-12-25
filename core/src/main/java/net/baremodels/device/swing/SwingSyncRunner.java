package net.baremodels.device.swing;

import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.model.Model;
import net.baremodels.model.NavigationContext;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleSyncRunner;
import net.baremodels.runner.SyncRunner;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public final class SwingSyncRunner
{
    private final ComponentListener componentListener;
    private final SwingSyncDevice syncDevice;
    private final SimpleSyncRunner syncRunner;
    private final boolean initialized;

    SwingSyncRunner(AppContext appContext, NavigationContext navigationContext, Model.Listener modelListener) {
        componentListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                onChange();
            }
        };
        syncDevice = SwingSyncDevice.newInstance(new DesktopIntentHandler(), componentListener);
        syncRunner = new SimpleSyncRunner(appContext, navigationContext, new SimpleModelRenderer(), syncDevice, modelListener);
        initialized = true;
    }

    /**
     * This mostly works.
     * The real solution is to make SwingAsyncRunner.
     */
    private void onChange() {
        if (initialized) {
            syncRunner.onChange(syncDevice.getDeviceState());
        }
    }

    public static SyncRunner newInstance(AppContext appContext, NavigationContext navigationContext, Model.Listener modelListener) {
        SwingSyncRunner swingSyncRunner = new SwingSyncRunner(appContext,navigationContext,modelListener);
        return swingSyncRunner.syncRunner;
    }
}