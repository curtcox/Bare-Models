package net.baremodels.ui;

import net.baremodels.model.Model;

import java.util.*;

public final class SimpleUIContainer
    implements UIContainer
{
    private final Model model;
    private final String name;
    private final List<UIComponent> components = new ArrayList<>();

    public static SimpleUIContainer of(Model model, UIComponent... components) {
        return of(model,model.toString(),components);
    }

    public static SimpleUIContainer of(Model model, String name, UIComponent... components) {
        return new SimpleUIContainer(model,name,components);
    }

    private SimpleUIContainer(Model model, String name, UIComponent... components) {
        this.model = model;
        this.name = name;
        for (UIComponent component : components) {
            this.components.add(component);
        }
    }

    @Override public Iterator<UIComponent>     iterator() { return components.iterator();  }
    @Override public int                           size() { return components.size(); }
    @Override public boolean                    isEmpty() { return components.isEmpty(); }
    @Override public boolean           contains(Object o) { return components.contains(o); }
    @Override public UIComponent[]              toArray() { return components.toArray(new UIComponent[0]); }
    @Override public <T> T[]               toArray(T[] a) { return components.toArray(a); }
    @Override public boolean add(UIComponent uiComponent) { return components.add(uiComponent); }
    @Override public boolean             remove(Object o) { return components.remove(o); }
    @Override public boolean containsAll(Collection<?> c) { return components.containsAll(c); }
    @Override public boolean addAll(Collection<? extends UIComponent> c) { return components.addAll(c); }
    @Override public boolean addAll(int index, Collection<? extends UIComponent> c) { return components.addAll(index,c); }
    @Override public boolean              removeAll(Collection<?> c) { return components.removeAll(c); }
    @Override public boolean              retainAll(Collection<?> c) { return components.retainAll(c); }
    @Override public void                                    clear() { components.clear();  }
    @Override public UIComponent                      get(int index) { return components.get(index); }
    @Override public UIComponent set(int index, UIComponent element) { return components.set(index,element); }
    @Override public void        add(int index, UIComponent element) { components.add(index,element);  }
    @Override public UIComponent                   remove(int index) { return components.remove(index); }
    @Override public int                           indexOf(Object o) { return components.indexOf(o); }
    @Override public int                       lastIndexOf(Object o) { return components.lastIndexOf(o); }
    @Override public        ListIterator<UIComponent> listIterator() { return components.listIterator();    }
    @Override public ListIterator<UIComponent> listIterator(int index) { return components.listIterator(index);    }
    @Override public List<UIComponent> subList(int fromIndex, int toIndex) { return components.subList(fromIndex,toIndex); }

    @Override public Model getInspectable() { return model;  }
    @Override public String getName() {  return name;  }

    @Override
    public String toString() {
        return String.format("%s%s", name, components);
    }
}
