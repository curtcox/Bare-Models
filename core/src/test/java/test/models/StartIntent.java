package test.models;

import net.baremodels.intent.Intent;

public class StartIntent
    extends Intent
{
    Car car;
    StartIntent(Car car) {
        super(car);
        this.car = car;
    }
}
