package net.baremodels.runner;

import net.baremodels.ui.UILayout.Constraints;

import java.util.Map;

public final class SimpleComponentConstraintSupplier
    implements ComponentConstraintSupplier
{
    private final Object layoutManager;
    private final Map<Constraints,String> layoutConstraints;

    public SimpleComponentConstraintSupplier(Object layoutManager, Map<Constraints, String> layoutConstraints) {
        this.layoutManager = layoutManager;
        this.layoutConstraints = layoutConstraints;
    }

    @Override
    final public <T> T getLayoutManager() {
        return (T) layoutManager;
    }

    @Override
    final public String getComponentConstraints(Constraints constraints) {
        return layoutConstraints.get(constraints);
    }
}
