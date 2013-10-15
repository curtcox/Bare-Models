package net.baremodels.awt;

import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public final class AwtRunner
    extends SimpleRunner
{
    public AwtRunner() {
        super(new SimpleModelRenderer(),AwtDevice.newInstance());
    }
}
