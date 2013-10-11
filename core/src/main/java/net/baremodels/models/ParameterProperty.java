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
    private final ModelFactory modelFactory;
    private final Map<String, Property> properties;

    ParameterProperty(Parameter parameter, ModelFactory modelFactory) {
        this.parameter = parameter;
        this.modelFactory = modelFactory;
        properties = new HashMap<>();
        properties.put(Property.NAME,new StringConstantProperty(parameter.getName(),modelFactory));
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
        return modelFactory.of(get());
    }

    @Override
    public Map<String, Property> meta() {
        return properties;
    }
}
