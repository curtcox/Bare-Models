package net.baremodels.device.vaadin;

import net.baremodels.runner.AsyncRunner;
import net.baremodels.runner.SimpleModelRenderer;

/**
 * A Runner for Vaadin.
 */
final class VaadinRunner
    extends AsyncRunner
{
    VaadinRunner(VaadinDevice device) {
        super(new SimpleModelRenderer(), device);
    }
}