package net.baremodels.device.swing;

import net.baremodels.model.Model;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public final class SwingRunner
    extends SimpleRunner
{
    public SwingRunner(Model.Listener listener) {
        super(new SimpleModelRenderer(), SwingDevice.newInstance(), listener);
    }
}