package net.baremodels.swing;

import net.baremodels.device.GenericDevice;
import net.baremodels.intent.Intent;
import net.baremodels.ui.UIComponent;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SwingDevice implements GenericDevice {

    private final JFrame frame;
    private final SwingComponentTranslator translator;

    private SwingDevice(JFrame frame) {
        this(frame,new SwingComponentTranslator());
    }

    private SwingDevice(JFrame frame, SwingComponentTranslator translator) {
        this.frame = frame;
        this.translator = translator;
    }

    public static SwingDevice newInstance() {
        FutureTask<JFrame> task = new FutureTask(new FrameMaker());
        try {
            EventQueue.invokeAndWait(task);
            return new SwingDevice(task.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException | InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public UIComponent display(UIComponent ui) {
        EventQueue.invokeLater(() -> _display(ui));
        return null;
    }

    private void _display(UIComponent ui) {
        frame.setContentPane(translator.translate(ui));
        frame.pack();
    }

    @Override
    public void onIntent(Intent intent) {

    }

    private static class FrameMaker implements Callable<JFrame>{
        @Override
        public JFrame call() throws Exception {
            JFrame frame = new JFrame();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500,500);
            return frame;
        }
    }

}
