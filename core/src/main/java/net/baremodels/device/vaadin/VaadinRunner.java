package net.baremodels.device.vaadin;

import net.baremodels.runner.AppContext;
import net.baremodels.runner.AsyncRunner;
import net.baremodels.runner.SimpleModelRenderer;

/**
 * A SyncRunner for Vaadin.
 */
final class VaadinRunner
    extends AsyncRunner
{
    VaadinRunner(AppContext appContext, VaadinDevice device) {
        super(appContext, new SimpleModelRenderer(), device);
    }
}