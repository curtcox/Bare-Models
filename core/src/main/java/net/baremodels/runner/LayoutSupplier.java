package net.baremodels.runner;

public interface LayoutSupplier {
    <T> T getLayoutManager();
    String getComponentConstraints(Object component);
}
