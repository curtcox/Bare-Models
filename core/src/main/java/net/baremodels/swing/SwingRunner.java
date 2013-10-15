package net.baremodels.swing;

import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public final class SwingRunner
    extends SimpleRunner
{
    public SwingRunner() {
        super(new SimpleModelRenderer(), SwingDevice.newInstance());
    }
}