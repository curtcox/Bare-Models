package net.baremodels.device.desktop;

import net.baremodels.intent.Intent;
import org.junit.Test;
import static org.junit.Assert.*;

public class DesktopIntentHandlerTest {

    DesktopIntentHandler testObject = new DesktopIntentHandler();

    @Test
    public void is_an_Intent_Listener() {
        assertTrue(new DesktopIntentHandler() instanceof Intent.Handler);
    }

    @Test
    public void onIntent_throws_IllegalArgumentException_for_unsupported_intents() {
        Intent intent = new Intent(null) {};
        try {
            testObject.onIntent(intent);
            fail();
        } catch (UnsupportedOperationException e) {
            String message = String.format("Unsupported intent %s:%s",intent.getClass(),intent.toString());
            assertEquals(message,e.getMessage());
        }
    }
}
