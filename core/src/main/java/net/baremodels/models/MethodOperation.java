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

/**
 * An implementation of Operation that wraps a single method.
 */
final class MethodOperation
    implements Operation
{

    private final Object object;
    private final Method method;
    private final ModelFactory modelFactory;
    private final List<Property> arguments;
    private final Map<String, Property> meta;

    MethodOperation(Object object, Method method, ModelFactory modelFactory) {
        this.object = object;
        this.method = method;
        this.modelFactory = modelFactory;
        arguments = determineArguments(method,modelFactory);
        meta = determineProperties(method,modelFactory);
    }

    private static List<Property> determineArguments(Method method, ModelFactory modelFactory) {
        List<Property> parameters = new ArrayList<>();
        for (Parameter parameter : method.getParameters()) {
            parameters.add(new ParameterProperty(parameter,modelFactory));
        }
        return parameters;
    }

    private static Map<String,Property> determineProperties(Method method, ModelFactory modelFactory) {
        Map<String, Property> meta = new HashMap<>();
        String name = method.getName();
        meta.put(Property.NAME, new StringConstantProperty(name, name, modelFactory));
        return meta;
    }

    @Override
    public List<Property> arguments() {
        return arguments;
    }

    @Override
    public Object invoke() {
        try {
            return mapResult(method.invoke(object, mapArguments()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            String message = object.getClass() + "." + method.getName();
            throw new RuntimeException(message,e);
        }
    }

    private Object mapResult(Object result) {
        if (result==null) {
            return null;
        }
        return result instanceof Intent ? result : modelFactory.of(result);
    }

    private Object[] mapArguments() {
        Object[] values = new Object[arguments.size()];
        for (int i=0; i<arguments.size(); i++) {
            values[i] = arguments.get(i).get();
        }
        return values;
    }

    @Override
    public Map<String, Property> meta() {
        return meta;
    }
}
