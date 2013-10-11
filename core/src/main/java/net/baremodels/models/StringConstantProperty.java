package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Property;

import java.util.Collections;
import java.util.Map;

final class StringConstantProperty
    implements Property
{
    private final String value;
    private final ModelFactory modelFactory;

    StringConstantProperty(String value, ModelFactory modelFactory) {
        this.value = value;
        this.modelFactory = modelFactory;
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
        return modelFactory.of(value);
    }

    @Override
    public Map<String, Property> meta() {
        Property name = new StringConstantProperty(NAME,modelFactory);
        return Collections.singletonMap(NAME,name);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        return value.equals(((StringConstantProperty) object).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
