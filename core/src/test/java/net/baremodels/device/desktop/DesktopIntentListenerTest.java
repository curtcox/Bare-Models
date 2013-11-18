package net.baremodels.device.desktop;

import net.baremodels.intent.Intent;
import org.junit.Test;
import static org.junit.Assert.*;

public class DesktopIntentListenerTest {

    @Test
    public void is_an_Intent_Listener() {
        assertTrue(new DesktopIntentListener() instanceof Intent.Listener);
    }
}
