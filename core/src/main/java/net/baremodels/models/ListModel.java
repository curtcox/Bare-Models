package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ListModel implements Model {

    public final List list;

    public ListModel(List list) {
        this.list = list;
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
            map.put(i,new ListElementProperty(list,i));
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
        ListModel that = (ListModel) o;
        return this.list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}
