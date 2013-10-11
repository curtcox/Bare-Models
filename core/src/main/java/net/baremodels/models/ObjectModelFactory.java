package net.baremodels.models;

import net.baremodels.model.Model;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

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
            String message = String.format("null should be used instead of ObjectModel.of(null,%s)",name);
            throw new NullPointerException(message);
        }
        if (models.containsKey(object)) {
            return models.get(object);
        }
        if (object instanceof List) {
            return newObjectListModel((List) object,name);
        }
        return newObjectModel(object);
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
