package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Property;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * A property backed by a field on an object.
 */
final class FieldProperty
    implements Property
{
    private final Field field;
    private final Object object;
    private final Map<String,Property> properties = new HashMap<>();

    FieldProperty(Object object,Field field) {
        this.object = object;
        this.field = field;
        properties.put(Property.NAME, new StringConstantProperty(field.getName()));
    }

    @Override
    public Object get() {
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot get " + object + "." + field.getName(),e);
        }
    }

    @Override
    public void set(Object value) {
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Model model() {
        return ObjectModel.of(get());
    }

    @Override
    public Map<String,Property> meta() {
        return properties;
    }

    @Override
    public String toString() {
        return "FieldProperty:" + field.getName();
    }
}
