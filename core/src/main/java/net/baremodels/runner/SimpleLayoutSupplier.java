package net.baremodels.runner;

import java.util.Map;

public class SimpleLayoutSupplier
    implements LayoutSupplier
{
    private final Object layoutManager;

    public SimpleLayoutSupplier(Object layoutManager, Map constraints) {
        this.layoutManager = layoutManager;
    }

    @Override
    final public <T> T getLayoutManager() {
        return (T) layoutManager;
    }

    @Override
    final public String getComponentConstraints(Object component) {
        return null;
    }
}
