package net.baremodels.device.awt;

import net.baremodels.model.Model;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

/**
 * A runner implementation that uses AWT.
 */
public final class AwtRunner
    extends SimpleRunner
{
    public AwtRunner(Model.Listener listener) {
        super(new SimpleModelRenderer(),AwtDevice.newInstance(), listener);
    }
}
