package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

final class MethodProperty
    implements Property
{
    private final Object object;
    private final Method method;

    public MethodProperty(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    @Override
    public Object get() {
        try {
            return method.invoke(object, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else {
                throw new RuntimeException(cause);
            }
        }
    }

    @Override
    public void set(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Model model() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Property> meta() {
        throw new UnsupportedOperationException();
    }
}
