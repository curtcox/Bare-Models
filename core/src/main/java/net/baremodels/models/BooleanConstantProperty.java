package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Property;

import java.util.Collections;
import java.util.Map;

final class BooleanConstantProperty
    implements Property
{
    private final String name;
    private final boolean value;
    private final ModelFactory modelFactory;

    BooleanConstantProperty(String name, boolean value, ModelFactory modelFactory) {
        this.name = name;
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
        Property nameProperty = new StringConstantProperty(NAME,name,modelFactory);
        return Collections.singletonMap(NAME,nameProperty);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public boolean equals(Object object) {
        return value == ((BooleanConstantProperty) object).value;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
