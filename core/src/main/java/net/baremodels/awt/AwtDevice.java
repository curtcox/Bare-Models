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

public class AwtDevice implements GenericDevice {

    private final Frame frame;
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
    public void display(UIComponent ui) {
        frame.add(translator.translate(ui));
        frame.pack();
        exitOnWindowClose();
    }

    private void exitOnWindowClose() {
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
            return frame;
        }
    }

}
