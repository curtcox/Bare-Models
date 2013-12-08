package net.baremodels.models;

import net.baremodels.model.Model;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * A ModelFactory that uses reflection on the given objects to produce ModelS.
 */
final class ObjectModelFactory
     implements ModelFactory
{
    /**
     * This method will return the same model if and only if given the same object.
     */
    @Override
    public Model of(Object object) {
        return modelWithName(object,"unnamed");
    }

    private static final Map<Object,Model> models = new IdentityHashMap<>();

    /**
     * This method will return the same model if and only if given the same object.
     */
    @Override
    public Model of(Object object, String name) {
        return modelWithName(object,name);
    }

    private Model modelWithName(Object object,String name) {
        if (object==null) {
            return NullModel.of();
        }
        if (object instanceof String) {
            return newStringModel(object);
        }
        if (object instanceof Boolean) {
            return newBooleanModel(object);
        }
        if (models.containsKey(object)) {
            return models.get(object);
        }
        if (object instanceof List) {
            return newObjectListModel((List) object,name);
        }
        return newObjectModel(object);
    }

    private Model newStringModel(Object object) {
        for (Map.Entry<Object,Model> entry : models.entrySet()) {
            if (object.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        StringConstantModel model = new StringConstantModel((String) object,this);
        models.put(object,model);
        return model;
    }

    private Model newBooleanModel(Object object) {
        return BooleanConstantModel.of((Boolean) object,this);
    }

    private Model newObjectModel(Object object) {
        ObjectModel model = new ObjectModel(object,this);
        models.put(object,model);
        return model;
    }

    private Model newObjectListModel(List list, String name) {
        ObjectListModel model = new ObjectListModel(list,name,this);
        models.put(list,model);
        return model;
    }

}
