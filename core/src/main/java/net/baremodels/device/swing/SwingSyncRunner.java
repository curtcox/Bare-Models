package net.baremodels.device.swing;

import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.model.Model;
import net.baremodels.runner.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * A runner implementation that uses Swing.
 */
public final class SwingSyncRunner
{
    private volatile boolean queued;
    private final ComponentListener componentListener;
    private final SwingSyncDevice syncDevice;
    private final SimpleSyncRunner syncRunner;
    private final boolean initialized;

    SwingSyncRunner(AppContext appContext, ModelContainerRenderer renderer, NextModelGenerator generator, Model.Listener modelListener) {
        componentListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                onChange();
            }
        };
        syncDevice = SwingSyncDevice.newInstance(appContext.config(),new DesktopIntentHandler(), componentListener);
        syncRunner = new SimpleSyncRunner(appContext, renderer, syncDevice, generator, modelListener);
        initialized = true;
    }

    /**
     * This mostly works.
     * The real solution is to make SwingAsyncRunner.
     */
    private void onChange() {
        if (initialized && !queued) {
            queued = true;
            syncRunner.onChange(syncDevice.getDeviceState());
            EventQueue.invokeLater(() -> queued = false);
        }
    }

    public static SyncRunner newInstance(AppContext appContext, ModelContainerRenderer renderer, NextModelGenerator generator, Model.Listener modelListener) {
        SwingSyncRunner swingSyncRunner = new SwingSyncRunner(appContext,renderer,generator,modelListener);
        return swingSyncRunner.syncRunner;
    }

}