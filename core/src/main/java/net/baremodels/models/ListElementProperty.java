package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ListElementProperty implements Property {
    private final List list;
    private final int index;
    private final Map properties = new HashMap();

    public ListElementProperty(List list, int index) {
        this.list = list;
        this.index = index;
        properties.put(Property.NAME,index + "");
    }

    @Override
    public Object get() {
        return list.get(index);
    }

    @Override
    public void set(Object o) {
    }

    @Override
    public Model model() {
        return ObjectModel.of(get());
    }

    @Override
    public Map<String, Object> meta() {
        return properties;
    }

}
