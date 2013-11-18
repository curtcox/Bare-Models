package net.baremodels.device.desktop;

import net.baremodels.intent.Intent;

/**
 * Intent listener for desktop Java.
 */
public final class DesktopIntentListener
    implements Intent.Listener
{
    @Override
    public void onIntent(Intent intent) {
        String message = String.format("Unsupported intent %s:%s",intent.getClass(),intent.toString());
        throw new UnsupportedOperationException(message);
    }
}
