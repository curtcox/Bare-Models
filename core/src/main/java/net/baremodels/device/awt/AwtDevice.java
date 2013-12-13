package net.baremodels.device.awt;

import net.baremodels.device.SyncDevice;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIComponent;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * A SyncDevice that uses AWT for widgets.
 */
final class AwtDevice
    implements SyncDevice
{

    private final Frame frame;
    private final WaitingComponentListener listener;
    private final Intent.Handler handler;
    private final SimpleComponentTranslator translator;

    private AwtDevice(Frame frame, Intent.Handler handler) {
        this(frame,
             new SimpleComponentTranslator(new AwtWidgetSupplier(), new SimpleComponentConstraintSupplier(new MigLayout(),new HashMap<>())),
             new WaitingComponentListener(), handler);
    }

    AwtDevice(Frame frame, SimpleComponentTranslator translator, WaitingComponentListener listener, Intent.Handler handler) {
        this.frame = frame;
        this.translator = translator;
        this.listener = listener;
        this.handler = handler;
    }

    public static AwtDevice newInstance(Intent.Handler handler) {
        FutureTask<Frame> task = new FutureTask(new FrameMaker());
        try {
            EventQueue.invokeAndWait(task);
            return new AwtDevice(task.get(),handler);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException | InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public Model display(UIComponent ui) {
        frame.removeAll();
        frame.add((Component) translator.translate(ui, listener));
        frame.setSize(1600, 980);
        frame.validate();
        return listener.waitForSelectionChange();
    }

    private static void exitOnWindowClose(Frame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void onIntent(Intent intent) {
        handler.onIntent(intent);
    }

    private static class FrameMaker implements Callable<Frame>{
        @Override
        public Frame call() throws Exception {
            Frame frame = new Frame();
            frame.setVisible(true);
            frame.setSize(500,500);
            exitOnWindowClose(frame);
            return frame;
        }
    }

}
