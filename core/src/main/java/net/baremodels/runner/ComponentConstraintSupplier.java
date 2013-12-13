package net.baremodels.runner;

public interface ComponentConstraintSupplier {
    <T> T getLayoutManager();
    String getComponentConstraints(Object component);
}
