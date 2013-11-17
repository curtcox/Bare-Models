package net.baremodels.uat;

import java.util.function.Predicate;

public final class Times implements Predicate {
    int count;
    private final int times;

    public Times(int times) {
        this.times = times;
    }

    @Override
    public boolean test(Object o) {
        return ++count > times;
    }
};