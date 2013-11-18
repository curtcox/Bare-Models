package net.baremodels.device.desktop;

import net.baremodels.intent.Intent;

/**
 * Intent handler for desktop Java.
 */
public final class DesktopIntentHandler
    implements Intent.Handler
{
    @Override
    public Intent onIntent(Intent intent) {
        String message = String.format("Unsupported intent %s:%s",intent.getClass(),intent.toString());
        throw new UnsupportedOperationException(message);
    }
}
