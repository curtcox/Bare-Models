package net.baremodels.device.swing;

import net.baremodels.runner.LayoutSupplier;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

final class SwingLayoutSupplier implements LayoutSupplier {
    @Override
    public LayoutManager getLayoutManager() {
        return new MigLayout();
    }

    @Override
    public String getComponentConstraints(Object component) {
        return null;
    }
}
