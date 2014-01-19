package net.baremodels.device.swing;

import net.baremodels.device.DeviceState;
import net.baremodels.device.SyncDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Inspectable;
import net.baremodels.runner.ContainerTranslator;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.SimpleContainerTranslator;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * A SyncDevice that uses Swing as the UI toolkit.
 */
final class SwingSyncDevice
    implements SyncDevice
{

    private final JFrame frame;
    private final Intent.Handler handler;
    private final WaitingComponentListener listener;
    private final ContainerTranslator translator;

    private SwingSyncDevice(JFrame frame, Intent.Handler handler) {
        this(frame, createTranslator(), new WaitingComponentListener(), handler);
    }

    private static SimpleContainerTranslator createTranslator() {
        return new SimpleContainerTranslator(
                new SwingWidgetSupplier(),
                new SimpleComponentConstraintSupplier(new MigLayout())
        );
    }

    SwingSyncDevice(JFrame frame, ContainerTranslator translator,
                    WaitingComponentListener listener, Intent.Handler handler)
    {
        this.frame = frame;
        this.translator = translator;
        this.listener = listener;
        this.handler = handler;
    }

    public static SwingSyncDevice newInstance(Intent.Handler handler, ComponentListener componentListener) {
        try {
            FutureTask<JFrame> task = new FutureTask(new SwingFrameMaker(componentListener));
            EventQueue.invokeAndWait(task);
            return new SwingSyncDevice(task.get(),handler);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException | InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public Inspectable display(UIContainer container, UILayout layout) {
        validateLayout(container, layout);
        EventQueue.invokeLater(() -> redisplay(container, layout));
        return listener.waitForSelectionChange();
    }

    private void validateLayout(UIContainer container, UILayout layout) {
        for (UIComponent component : container) {
            if (layout.getConstraints(component)==null) {
                String message = String.format("Missing constraints for %s", component);
                throw new IllegalArgumentException(message);
            }
        }
    }

    @Override
    public DeviceState getDeviceState() {
        return new DeviceState(frame.getWidth(),frame.getHeight());
    }

    public void redisplay(UIContainer container, UILayout layout) {
        validateLayout(container, layout);
        if (!EventQueue.isDispatchThread()) {
            throw new IllegalThreadStateException("Must only be called from the EDT.");
        }
        Container translated = translator.translate(container, layout, listener);
        frame.setContentPane(translated);
        frame.validate();
    }

    @Override
    public void onIntent(Intent intent) {
        handler.onIntent(intent);
    }

}
