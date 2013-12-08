package test.models;

public class Key {
    private final Car car;

    public Key(Car car) {
        this.car = car;
    }

    public StartIntent start() {
        return new StartIntent(car);
    }

}
