package net.baremodels.device.text;

import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.ui.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TextWidgetSupplierTest {

    String value = random("value");

    Model model = ModelFactory.DEFAULT.of(value);
    UIComponent.Listener listener;
    String name = random("name");
    ComponentConstraintSupplier componentConstraintSupplier = new SimpleComponentConstraintSupplier(null);

    TextWidgetSupplier testObject = new TextWidgetSupplier();

    private String random(String string) {
        return this + string;
    }


    @Test
    public void empty_list() {
        Map<?, Property> properties = new HashMap<>();
        ListModel listModel = new ListModel() {
            @Override public List                 getList() { return null;       }
            @Override public Map<?, Property>  properties() { return properties; }
            @Override public Map<?, Operation> operations() { return null;       }
            @Override public Map<String, Property>   meta() { return null;       }
        };

        UIList ui = new UIList(listModel,name);

        String actual = testObject.list(ui, listener);

        String expected = String.format("%s[]",name);
        assertEquals(expected, actual);
    }

    @Test
    public void one_item_list() {
        String item = random("item");
        Model model = new Model() {
            @Override public Map<?, Property>  properties() { return null; }
            @Override public Map<?, Operation> operations() { return null; }
            @Override public String                  name() { return item; }
            @Override public Map<String, Property>   meta() { return null; }
        };
        Property property = new Property() {
            @Override public Object                 get() { return null; }
            @Override public void           set(Object o) {}
            @Override public Model                model() { return model; }
            @Override public Map<String, Property> meta() { return null; }
        };
        Map properties = new HashMap<>();
        properties.put(0,property);
        ListModel listModel = new ListModel() {
            @Override public List                 getList() { return null; }
            @Override public Map<?, Property>  properties() { return properties; }
            @Override public Map<?, Operation> operations() { return null; }
            @Override public Map<String, Property>   meta() { return null; }
        };

        UIList ui = new UIList(listModel,name);

        String actual = testObject.list(ui, listener);

        String expected = String.format("%s[%s]",name,item);
        assertEquals(expected, actual);
    }

    @Test
    public void button() {
        UIButton ui = new UIButton(model);
        String actual = testObject.button(ui, listener);

        String expected = String.format("[%s]",value);
        assertEquals(expected, actual);
    }

    @Test
    public void container_with_a_label() {
        List<String> components = Arrays.asList("label");
        UIContainer container = SimpleUIContainer.of(model);
        UILayout layout = new UILayout(new HashMap<>());

        String actual = testObject.container(container, layout, components, componentConstraintSupplier);

        assertEquals("[label]", actual);
    }

    @Test
    public void empty_container() {
        List<UIComponent> components = Arrays.asList();
        UIContainer container = SimpleUIContainer.of(model,name,components.toArray(new UIComponent[0]));
        UILayout layout = new UILayout(new HashMap<>());
        String actual = testObject.container(container, layout, components, componentConstraintSupplier);

        assertEquals("[]", actual);
    }

}
