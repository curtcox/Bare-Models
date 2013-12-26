package net.baremodels.device.awt;

import net.baremodels.device.DeviceState;
import net.baremodels.device.SyncDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.ContainerTranslator;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.SimpleContainerTranslator;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ComponentListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * A SyncDevice that uses AWT for widgets.
 */
final class AwtSyncDevice
    implements SyncDevice
{

    private final Frame frame;
    private final WaitingComponentListener listener;
    private final Intent.Handler handler;
    private final ContainerTranslator translator;

    private AwtSyncDevice(Frame frame, Intent.Handler handler) {
        this(frame,
             new SimpleContainerTranslator(new AwtWidgetSupplier(), new SimpleComponentConstraintSupplier(new MigLayout(),new HashMap<>())),
             new WaitingComponentListener(), handler);
    }

    AwtSyncDevice(Frame frame, ContainerTranslator translator, WaitingComponentListener listener, Intent.Handler handler) {
        this.frame = frame;
        this.translator = translator;
        this.listener = listener;
        this.handler = handler;
    }

    public static AwtSyncDevice newInstance(Intent.Handler handler, ComponentListener componentListener) {
        try {
            FutureTask<Frame> task = new FutureTask(new AwtFrameMaker(componentListener));
            EventQueue.invokeAndWait(task);
            return new AwtSyncDevice(task.get(),handler);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException | InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public Model display(UIContainer container, UILayout layout) {
        redisplay(container, layout);
        return listener.waitForSelectionChange();
    }

    @Override
    public void redisplay(UIContainer container, UILayout layout) {
        frame.removeAll();
        Component translated = translator.translate(container, layout, listener);
        frame.add(translated);
        frame.validate();
    }

    @Override
    public DeviceState getDeviceState() {
        return new DeviceState(frame.getWidth(),frame.getHeight());
    }

    @Override
    public void onIntent(Intent intent) {
        handler.onIntent(intent);
    }

}
