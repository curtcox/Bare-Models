package net.baremodels.device.swing;

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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

final class SwingDevice
    implements SyncDevice
{

    private final JFrame frame;
    private final Intent.Handler handler;
    private final WaitingComponentListener listener;
    private final ContainerTranslator translator;

    private SwingDevice(JFrame frame, Intent.Handler handler) {
        this(frame,
        new SimpleContainerTranslator(new SwingWidgetSupplier(), new SimpleComponentConstraintSupplier(new MigLayout(), new HashMap<>())),
        new WaitingComponentListener(), handler);
    }

    SwingDevice(JFrame frame, ContainerTranslator translator,
        WaitingComponentListener listener, Intent.Handler handler)
    {
        this.frame = frame;
        this.translator = translator;
        this.listener = listener;
        this.handler = handler;
    }

    public static SwingDevice newInstance(Intent.Handler handler) {
        try {
            ComponentListener componentListener = new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                }
            };
            FutureTask<JFrame> task = new FutureTask(new SwingFrameMaker(componentListener));
            EventQueue.invokeAndWait(task);
            return new SwingDevice(task.get(),handler);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException | InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public Model display(UIContainer ui, UILayout layout) {
        EventQueue.invokeLater(() -> _display(ui,layout));
        return listener.waitForSelectionChange();
    }

    private void _display(UIContainer ui, UILayout layout) {
        frame.setContentPane((Container) translator.translate(ui, layout, listener));
        frame.setSize(1600,980);
        frame.validate();
    }

    @Override
    public void onIntent(Intent intent) {
        handler.onIntent(intent);
    }

}
