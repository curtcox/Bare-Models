package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ListModel implements Model {

    private final List list;

    public ListModel(List list) {
        this.list = list;
    }

    @Override
    public Map<Integer, Property> properties() {
        Map map = new LinkedHashMap();
        for (int i=0; i<list.size(); i++) {
            map.put(i,new ListElementProperty(list,i));
        }
        return map;
    }

    @Override
    public Map<String, Operation> operations() {
        return null;
    }
}
