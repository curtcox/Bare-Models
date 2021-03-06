package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * A Model that is backed by an object.
 * Reflection is used to access the object properties and operations.
 * @author curt
 */
final class ObjectModel
    implements Model
{
    final Object object;
    final ModelFactory modelFactory;
    final Map<String,Property> properties;
    final Map<String,Operation> operations;

    /**
     * This method should only be used by tests and ObjectModelFactory.
     * Anything else that needs to create a Model should have a ModelFactory.
     */
    ObjectModel(Object object, ModelFactory modelFactory) {
        this.object = object;
        this.modelFactory = modelFactory;
        this.properties = determineProperties(object);
        this.operations = determineOperations(object);
    }

    private Map<String,Property> determineProperties(Object object) {
        Map<String,Property> properties =  new LinkedHashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!Modifier.isPrivate(field.getModifiers()) && !Modifier.isTransient(field.getModifiers())) {
                properties.put(field.getName(),new FieldProperty(object,field,modelFactory));
            }
        }
        return properties;
    }

    private Map<String,Operation> determineOperations(Object object) {
        Map<String,Operation> operations =  new TreeMap<>();
        for (Method method : object.getClass().getDeclaredMethods()) {
            operations.put(method.getName(), new MethodOperation(object, method,modelFactory));
        }
        return operations;
    }

    @Override
    public Map<String,Property> properties() {
        return properties;
    }

    @Override
    public Map<String,Operation> operations() {
        return operations;
    }

    @Override
    final public boolean equals(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof ObjectModel)) {
            return false;
        }
        ObjectModel that = (ObjectModel) other;
        return object == that.object;
    }

    @Override
    final public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        if (hasOverriddenToString()) {
            return object.toString();
        }
        if (hasDefinedValue()) {
            return getValue();
        }
        if (hasDefinedName()) {
            return getName();
        }
        return object.toString();
    }

    private boolean hasDefinedName() {
        return !(getNameProperty() instanceof StringConstantProperty);
    }

    private boolean hasDefinedValue() {
        return properties().containsKey(VALUE);
    }

    private boolean hasOverriddenToString() {
        String standard = object.getClass().getName() + "@" + Integer.toHexString(object.hashCode());
        return !object.toString().equals(standard);
    }

    private String getValue() {
        return properties().get(VALUE).get().toString();
    }

    private String getName() {
        return String.valueOf(getNameProperty().get());
    }

    @Override
    public Map<String, Property> meta() {
        return Collections.singletonMap(NAME, getNameProperty());
    }

    private Property getNameProperty() {
        Method method = getNameMethod();
        if (method!=null) {
            return new MethodProperty(object,method);
        }
        Field field = getNameField();
        if (field!=null) {
            return new FieldProperty(object,field,modelFactory);
        }
        return new StringConstantProperty(NAME,object.getClass().getSimpleName(),modelFactory);
    }

    private Method getNameMethod() {
        try {
            return object.getClass().getDeclaredMethod(NAME,new Class[0]);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private Field getNameField() {
        try {
            return object.getClass().getField(NAME);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

}
