package net.baremodels.device.awt;

import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Callable;

public class AwtFrameMaker implements Callable<Frame> {

    private final ComponentListener listener;

    AwtFrameMaker(ComponentListener listener) {
        this.listener = listener;
    }

    @Override
    public Frame call() throws Exception {
        Frame frame = new Frame();
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.addComponentListener(listener);
        exitOnWindowClose(frame);
        return frame;
    }

    private static void exitOnWindowClose(Frame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
