package net.baremodels.runner;

import java.util.Map;

public final class SimpleLayoutSupplier
    implements LayoutSupplier
{
    private final Object layoutManager;
    private final Map<Object,String> constraints;

    public SimpleLayoutSupplier(Object layoutManager, Map<Object,String> constraints) {
        this.layoutManager = layoutManager;
        this.constraints = constraints;
    }

    @Override
    final public <T> T getLayoutManager() {
        return (T) layoutManager;
    }

    @Override
    final public String getComponentConstraints(Object component) {
        return constraints.get(component);
    }
}
