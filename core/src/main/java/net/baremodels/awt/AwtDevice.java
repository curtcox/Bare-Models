package net.baremodels.awt;

import net.baremodels.device.GenericDevice;
import net.baremodels.intent.Intent;
import net.baremodels.ui.UIComponent;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.BooleanSupplier;

public class AwtDevice implements GenericDevice {

    private final Frame frame;
    private final AwtComponentListener listener = new AwtComponentListener();
    private final AwtComponentTranslator translator;

    private AwtDevice(Frame frame) {
        this(frame,new AwtComponentTranslator());
    }

    private AwtDevice(Frame frame, AwtComponentTranslator translator) {
        this.frame = frame;
        this.translator = translator;
    }

    public static AwtDevice newInstance() {
        FutureTask<Frame> task = new FutureTask(new FrameMaker());
        try {
            EventQueue.invokeAndWait(task);
            return new AwtDevice(task.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException | InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public UIComponent display(UIComponent ui) {
        frame.removeAll();
        frame.add(translator.translate(ui,listener));
        frame.pack();
        waitUntil(()->listener.selected !=null);
        UIComponent selected = listener.selected;
        System.out.println("selected 2 = " + selected);
        listener.selected = null;
        return selected;
    }

    private void waitUntil(BooleanSupplier condition) {
        while (!condition.getAsBoolean()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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
