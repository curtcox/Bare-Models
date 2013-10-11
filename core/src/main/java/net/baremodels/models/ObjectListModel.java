package net.baremodels.models;

import net.baremodels.model.ListModel;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A ListModel that provides access to an underlying list.
 */
final class ObjectListModel
    implements ListModel
{

    final List list;
    final String name;
    private final ModelFactory modelFactory;

    ObjectListModel(List list, String name, ModelFactory modelFactory) {
        this.list = list;
        this.name = name;
        this.modelFactory = modelFactory;
    }

    @Override
    public List getList() {
        return list;
    }

    @Override
    public Map<String, Property> meta() {
        return Collections.singletonMap(Property.NAME, getNameProperty());
    }

    private Property getNameProperty() {
        return new StringConstantProperty(name,modelFactory);
    }

    private class ListPropertyMap extends LinkedHashMap {
        public Object get(Object key) {
            if (isValidIndex(key)) {
                return super.get(key);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        private boolean isValidIndex(Object key) {
            if (!(key instanceof Integer)) {
                return false;
            }
            int index = (Integer) key;
            return index > -1 && index < list.size();
        }
    }

    @Override
    public Map<Integer, Property> properties() {
        Map map = new ListPropertyMap();
        for (int i=0; i<list.size(); i++) {
            map.put(i,new ListElementProperty(list,i,modelFactory));
        }
        return map;
    }

    @Override
    public Map<String, Operation> operations() {
        return null;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public boolean equals(Object o) {
        ObjectListModel that = (ObjectListModel) o;
        return this.list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}
