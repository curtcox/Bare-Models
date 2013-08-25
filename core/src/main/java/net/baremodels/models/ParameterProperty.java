package net.baremodels.models;

import net.baremodels.model.Property;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

final class ParameterProperty implements Property {

    private Object value;
    private final Parameter parameter;
    private final Map<String, Object> properties;

    ParameterProperty(Parameter parameter) {
        this.parameter = parameter;
        properties = new HashMap<>();
        properties.put(Property.NAME,parameter.getName());
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
    public Map<String, Object> properties() {
        return properties;
    }
}
