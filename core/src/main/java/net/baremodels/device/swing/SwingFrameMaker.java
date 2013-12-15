package net.baremodels.device.swing;

import javax.swing.*;
import java.awt.event.ComponentListener;
import java.util.concurrent.Callable;

final class SwingFrameMaker
    implements Callable<JFrame>
{
    private final ComponentListener listener;

    SwingFrameMaker(ComponentListener listener) {
        this.listener = listener;
    }

    @Override
    public JFrame call() throws Exception {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.addComponentListener(listener);
        return frame;
    }
}
