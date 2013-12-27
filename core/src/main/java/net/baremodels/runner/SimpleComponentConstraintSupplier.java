package net.baremodels.runner;

import net.baremodels.ui.UILayout.Constraints;

public final class SimpleComponentConstraintSupplier
    implements ComponentConstraintSupplier
{
    private final Object layoutManager;

    public SimpleComponentConstraintSupplier(Object layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    final public <T> T getLayoutManager() {
        return (T) layoutManager;
    }

    @Override
    final public String getComponentConstraints(Constraints constraints) {
        return constraints.value;
    }
}
