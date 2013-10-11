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
    @Deprecated
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
    final public boolean equals(Object object) {
        if (this==object) {
            return true;
        }
        ObjectModel that = (ObjectModel) object;
        if (!getClass().equals(that.getClass())) {
            return false;
        }
        Map mine = properties();
        Map theirs = that.properties();
        
        return mine.equals(theirs);
    }

    @Override
    final public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return object.toString();
    }

    @Override
    public Map<String, Property> meta() {
        return Collections.singletonMap(Property.NAME, getNameProperty());
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
        return new StringConstantProperty(object.getClass().getSimpleName(),modelFactory);
    }

    private Method getNameMethod() {
        try {
            return object.getClass().getDeclaredMethod("name",new Class[0]);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private Field getNameField() {
        try {
            return object.getClass().getField("name");
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}
