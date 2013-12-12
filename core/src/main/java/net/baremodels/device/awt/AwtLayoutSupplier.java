package net.baremodels.device.awt;

import net.baremodels.runner.SimpleLayoutSupplier;
import net.miginfocom.swing.MigLayout;

import java.util.Map;

final class AwtLayoutSupplier
    extends SimpleLayoutSupplier
{
    AwtLayoutSupplier(Map constraints) {
        super(new MigLayout(), constraints);
    }
}
