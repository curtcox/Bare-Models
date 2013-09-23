package net.baremodels.util;

import java.util.function.BooleanSupplier;

public class TimeUtil {
    public static void waitUntil(BooleanSupplier condition) {
        while (!condition.getAsBoolean()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
