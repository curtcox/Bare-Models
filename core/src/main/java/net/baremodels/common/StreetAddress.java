package net.baremodels.common;

import net.baremodels.intents.MapAddressIntent;

public class StreetAddress {
    public String line1;
    public String line2;
    public String city;
    public String state;
    public String zip;

    @Override
    public String toString() {
        return String.format("%s, %s, %s %s",line1,city,state,zip);
    }

    public MapAddressIntent map() {
        return MapAddressIntent.of(this);
    }
}
