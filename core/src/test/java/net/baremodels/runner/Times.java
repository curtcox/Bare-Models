package net.baremodels.runner;

import java.util.function.Predicate;

public final class Times implements Predicate {
    int count;
    private final int times;

    Times(int times) {
        this.times = times;
    }

    @Override
    public boolean test(Object o) {
        return ++count > times;
    }
};