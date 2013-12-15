package net.baremodels.device.swing;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentListener;
import java.util.Arrays;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SwingFrameMakerTest {

    ComponentListener listener = new ComponentAdapter(){};
    SwingFrameMaker testObject = new SwingFrameMaker(listener);

    @Test
    public void is_a_callable() {
        assertTrue(testObject instanceof Callable);
    }

    @Test
    public void call_returns_a_JFrame() throws Exception {
        assertTrue(testObject.call() instanceof JFrame);
    }

    @Test
    public void call_configures_JFrame() throws Exception {
        JFrame actual =  testObject.call();
        assertEquals(new Dimension(500,500),actual.getSize());
        assertTrue(actual.isVisible());
        assertEquals(JFrame.EXIT_ON_CLOSE,actual.getDefaultCloseOperation());
    }

    @Test
    public void call_add_component_listener_from_constructor() throws Exception {
        JFrame actual =  testObject.call();

        assertTrue(Arrays.asList(actual.getComponentListeners()).contains(listener));
    }

}
