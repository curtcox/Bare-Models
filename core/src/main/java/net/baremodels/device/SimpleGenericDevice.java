package net.baremodels.device;

import net.baremodels.intent.Intent;
import net.baremodels.ui.UIComponent;

public final class SimpleGenericDevice implements GenericDevice {

    private final UIRenderer renderer;
    private final DeviceDriver device;

    public SimpleGenericDevice(UIRenderer renderer, DeviceDriver device) {
        this.renderer = renderer;
        this.device = device;
    }

    @Override
    public void display(UIComponent ui) {
        device.display(renderer.render(ui));
    }

    @Override
    public void onIntent(Intent intent) {
        device.onIntent(intent);
    }
}
