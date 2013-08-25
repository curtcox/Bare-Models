package net.baremodels.device;

import net.baremodels.intent.Intent;

public interface DeviceDriver extends Intent.Listener {
    void display(Object component);
}
