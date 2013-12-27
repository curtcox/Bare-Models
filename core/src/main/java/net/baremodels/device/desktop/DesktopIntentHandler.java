package net.baremodels.device.desktop;

import net.baremodels.intent.Intent;
import net.baremodels.intents.EmailIntent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Intent handler for desktop Java.
 */
public final class DesktopIntentHandler
    implements Intent.Handler
{
    private final Desktop desktop = Desktop.getDesktop();

    @Override
    public Intent onIntent(Intent intent) {
        if (intent instanceof EmailIntent) {
            handle((EmailIntent) intent);
            return null;
        }
        return intent;
    }

    private void handle(EmailIntent intent) {
        try {
            URI uri = new URI(String.format("mailto:?to=%s",intent.address.value));
            desktop.mail(uri);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
