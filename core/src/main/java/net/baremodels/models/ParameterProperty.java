package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Property;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

final class ParameterProperty
    implements Property
{

    private Object value;
    private final Parameter parameter;
    private final Map<String, Property> properties;

    ParameterProperty(Parameter parameter) {
        this.parameter = parameter;
        properties = new HashMap<>();
        properties.put(Property.NAME,new StringConstantProperty(parameter.getName()));
    }

    @Override
    public Object get() {
        return value;
    }

    @Override
    public void set(Object value) {
        this.value = value;
    }

    @Override
    public Model model() {
        return ObjectModel.of(get());
    }

    @Override
    public Map<String, Property> meta() {
        return properties;
    }
}
