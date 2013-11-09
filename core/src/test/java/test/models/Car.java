package test.models;

import java.util.Arrays;
import java.util.List;

public class Car {
    public Key key = new Key(this);
    public List<Part> parts = Arrays.asList(new Part());
    public List<Passenger> passengers = Arrays.asList(new Passenger("Moe"), new Passenger("Larry"), new Passenger("Curly"));
}
