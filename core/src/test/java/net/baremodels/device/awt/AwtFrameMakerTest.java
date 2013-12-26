package net.baremodels.device.awt;

import org.junit.Test;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.util.Arrays;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AwtFrameMakerTest {

    ComponentListener listener = new ComponentAdapter(){};
    AwtFrameMaker testObject = new AwtFrameMaker(listener);

    @Test
    public void is_a_callable() {
        assertTrue(testObject instanceof Callable);
    }

    @Test
    public void call_returns_a_Frame() throws Exception {
        assertTrue(testObject.call() instanceof Frame);
    }

    @Test
    public void call_configures_Frame() throws Exception {
        Frame actual =  testObject.call();
        assertEquals(new Dimension(500,500),actual.getSize());
        assertTrue(actual.isVisible());
        assertTrue(actual.getWindowListeners()[0] instanceof WindowAdapter);
    }

    @Test
    public void call_add_component_listener_from_constructor() throws Exception {
        Frame actual =  testObject.call();

        assertTrue(Arrays.asList(actual.getComponentListeners()).contains(listener));
    }

}
