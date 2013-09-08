package net.baremodels.models;

import net.baremodels.intent.Intent;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class MethodOperation implements Operation {

    private final Object object;
    private final Method method;
    private final List<Property> arguments;
    private final Map<String, Object> properties;

    MethodOperation(Object object, Method method) {
        this.object = object;
        this.method = method;
        arguments = determineArguments(method);
        properties = determineProperties(method);
    }

    private static List<Property> determineArguments(Method method) {
        List<Property> parameters = new ArrayList<>();
        for (Parameter parameter : method.getParameters()) {
            parameters.add(new ParameterProperty(parameter));
        }
        return parameters;
    }

    private Map<String,Object> determineProperties(Method method) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Property.NAME,method.getName());
        return properties;
    }

    @Override
    public List<Property> arguments() {
        return arguments;
    }

    @Override
    public Object invoke() {
        try {
            Object[] values = new Object[arguments.size()];
            for (int i=0; i<arguments.size(); i++) {
                values[i] = arguments.get(i).get();
            }
            Object result = method.invoke(object,values);
            if (result==null) {
                return null;
            }
            return result instanceof Intent ? result : ObjectModel.of(result);
        } catch (IllegalAccessException | InvocationTargetException e) {
            String message = object.getClass() + "." + method.getName();
            throw new RuntimeException(message,e);
        }
    }

    @Override
    public Map<String, Object> properties() {
        return properties;
    }
}
