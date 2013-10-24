package net.baremodels.awt;

import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

/**
 * A runner implementation that uses AWT.
 */
public final class AwtRunner
    extends SimpleRunner
{
    public AwtRunner() {
        super(new SimpleModelRenderer(),AwtDevice.newInstance());
    }
}
