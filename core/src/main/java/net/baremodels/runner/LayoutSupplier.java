package net.baremodels.runner;

import java.awt.*;

public interface LayoutSupplier {
    LayoutManager getLayoutManager();
    String getComponentConstraints(Object component);
}
