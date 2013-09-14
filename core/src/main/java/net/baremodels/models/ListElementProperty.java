package net.baremodels.models;

import net.baremodels.model.Model;
import net.baremodels.model.Property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ListElementProperty
    implements Property
{
    private final List list;
    private final int index;
    private final Map<String,Property> properties = new HashMap<>();

    public ListElementProperty(List list, int index) {
        this.list = list;
        this.index = index;
        properties.put(Property.NAME,new StringConstantProperty(index + ""));
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
    public Map<String, Property> meta() {
        return properties;
    }

    @Override
    public String toString() {
        return get().toString();
    }
}
