package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Skeletal implementation of Model.
 * @author curt
 */
public class ObjectModel
    implements Model
{
    public final Object object;
    public final Map<String,Property> properties;
    public final Map<String,Operation> operations;

    private static final Map<Object,ObjectModel> models = new HashMap<>();

    public static ObjectModel of(Object object) {
        if (object==null) {
            throw new NullPointerException("null should be used instead of ObjectModel.of(null)");
        }
        if (models.containsKey(object)) {
            return models.get(object);
        }
        ObjectModel model = new ObjectModel(object);
        models.put(object,model);
        return model;
    }

    private ObjectModel(Object object) {
        this.object = object;
        this.properties = determineProperties(object);
        this.operations = determineOperations(object);
    }

    private Map<String,Property> determineProperties(Object object) {
        Map<String,Property> properties =  new LinkedHashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!Modifier.isPrivate(field.getModifiers())) {
                properties.put(field.getName(),new FieldProperty(object,field));
            }
        }
        return properties;
    }

    private Map<String,Operation> determineOperations(Object object) {
        Map<String,Operation> operations =  new TreeMap<>();
        for (Method method : object.getClass().getDeclaredMethods()) {
            operations.put(method.getName(), new MethodOperation(object, method));
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

}
