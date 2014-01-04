package net.baremodels.device.awt;

import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.model.Model;
import net.baremodels.runner.NavigationContext;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleSyncRunner;
import net.baremodels.runner.SyncRunner;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * A runner implementation that uses AWT.
 */
public final class AwtSyncRunner
{
    private volatile boolean queued;
    private final ComponentListener componentListener;
    private final AwtSyncDevice syncDevice;
    private final SimpleSyncRunner syncRunner;
    private final boolean initialized;

    AwtSyncRunner(AppContext appContext, NavigationContext navigationContext, Model.Listener modelListener) {
        componentListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                onChange();
            }
        };
        syncDevice = AwtSyncDevice.newInstance(new DesktopIntentHandler(), componentListener);
        syncRunner = new SimpleSyncRunner(appContext, navigationContext, syncDevice, modelListener);
        initialized = true;
    }

    /**
     * This mostly works.
     * The real solution is to make AwtAsyncRunner.
     */
    private void onChange() {
        if (initialized && !queued) {
            queued = true;
            syncRunner.onChange(syncDevice.getDeviceState());
            EventQueue.invokeLater(() -> queued = false);
        }
    }

    public static SyncRunner newInstance(AppContext appContext, NavigationContext navigationContext, Model.Listener modelListener) {
        AwtSyncRunner swingSyncRunner = new AwtSyncRunner(appContext,navigationContext,modelListener);
        return swingSyncRunner.syncRunner;
    }
}
