package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Property;

import java.util.Collections;
import java.util.Map;

final class StringConstantProperty
    implements Property
{
    private final String name;
    private final String value;
    private final ModelFactory modelFactory;

    StringConstantProperty(String name, String value, ModelFactory modelFactory) {
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
        throw new UnsupportedOperationException("Cannot be pickModelFrom");
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
