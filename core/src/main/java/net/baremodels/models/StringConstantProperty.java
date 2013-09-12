package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Property;

import java.util.Map;

final class StringConstantProperty
    implements Property
{
    private final String value;

    StringConstantProperty(String value) {
        this.value = value;
    }

    @Override
    public Object get() {
        return value;
    }

    @Override
    public void set(Object o) {
        throw new UnsupportedOperationException("Cannot be set");
    }

    @Override
    public Model model() {
        return ObjectModel.of(value);
    }

    @Override
    public Map<String, Property> meta() {
        return null;
    }
}
