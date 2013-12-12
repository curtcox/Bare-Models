package net.baremodels.device.vaadin;

import com.vaadin.ui.FormLayout;
import net.baremodels.runner.SimpleLayoutSupplier;

import java.util.Map;

final class VaadinLayoutSupplier
    extends SimpleLayoutSupplier
{
    VaadinLayoutSupplier(Map constraints) {
        super(new FormLayout(),constraints);
    }
}
