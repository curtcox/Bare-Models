package net.baremodels.device.swing;

import net.baremodels.runner.SimpleLayoutSupplier;
import net.miginfocom.swing.MigLayout;

import java.util.Map;

final class SwingLayoutSupplier
    extends SimpleLayoutSupplier
{
    SwingLayoutSupplier(Map constraints) {
        super(new MigLayout(), constraints);
    }
}
