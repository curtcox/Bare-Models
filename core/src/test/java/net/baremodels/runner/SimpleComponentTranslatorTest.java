package net.baremodels.runner;

import ionic.app.NucleusTestFactory;
import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.model.Operation;
import net.baremodels.model.Property;
import net.baremodels.device.text.TextWidgetSupplier;
import net.baremodels.ui.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SimpleComponentTranslatorTest {

    Model nucleus = NucleusTestFactory.newNucleusModel();
    UIComponent ui = new SimpleModelRenderer().render(nucleus);
    private final SimpleComponentListener listener = new SimpleComponentListener();

    SimpleComponentTranslator testObject = new SimpleComponentTranslator(new TextWidgetSupplier());

    @Test
    public void nucleus_shows_all_top_level_elements() {
        String actual = testObject.translate(ui,listener);
        assertContains(actual,"Nucleus");
        assertContains(actual,"[Teams]");
        assertContains(actual,"[Users]");
        assertContains(actual,"[Badges]");
        assertContains(actual,"[Skills]");
    }

    @Test
    public void label_is_label_name() {
        UILabel uiLabel = new UILabel("label text");
        String actual = testObject.translate(uiLabel, listener);
        assertEquals("label text",actual);
    }

    @Test
    public void button_contains_button_name_with_brackets() {
        UIButton uiButton = new UIButton(nucleus,"button name");
        String actual = testObject.translate(uiButton,listener);
        assertEquals("[button name]",actual);
    }

    @Test
    public void container_contains_contents_separated_by_commas() {
        UILabel label1 = new UILabel("label1");
        UILabel label2 = new UILabel("label2");
        UIContainer container = SimpleUIContainer.of(nucleus, label1, label2);

        String actual = testObject.translate(container, listener);

        assertEquals("[label1, label2]",actual);
    }

    static class FakeModel implements Model {
        @Override public Map<?, Property> properties() { return null; }
        @Override public Map<?, Operation> operations() { return null; }
        @Override public Map<String, Property> meta() { return null; }
        @Override public String name() { return null; }
    }

    static class FakeProperty implements Property {
        @Override public Object get() { return null; }
        @Override public void set(Object o) {}
        @Override public Model model() { return null; }
        @Override public Map<String, Property> meta() { return null; }
    }

    static class FakeListModel implements ListModel {
        @Override public List getList() { return null; }
        @Override public Map<?, Property> properties() { return null; }
        @Override public Map<?, Operation> operations() { return null; }
        @Override public Map<String, Property> meta() { return null; }
    };

    @Test
    public void list_contents_separated_by_commas() {
        String name = "items";
        List list = new ArrayList();
        list.add("item1");
        list.add("item2");
        Map properties = new LinkedHashMap<>();
        class ItemModel extends FakeModel {
            private final String item;
            public ItemModel(String item) {
                this.item = item;
            }
            @Override public String name() { return item; }
        }
        class ItemProperty extends FakeProperty {
            private final String item;
            ItemProperty(String item) { this.item = item; }
            @Override public Object get() { return item; }
            @Override public Model model() { return new ItemModel(item); }
        }
        properties.put(0,new ItemProperty("item1"));
        properties.put(1,new ItemProperty("item2"));
        ListModel listModel = new FakeListModel() {
            @Override public List getList() { return list; }
            @Override public Map<?, Property> properties() { return properties; }
        };
        UIList uiList = new UIList(listModel, name);

        String actual = testObject.translate(uiList,listener);

        assertEquals("items[item1, item2]",actual);
    }

    @Test
    public void nucleus_has_no_extra_characters() {
        String actual = testObject.translate(ui,listener);
        assertFalse(actual, actual.contains("@"));
    }

    private void assertContains(String containing, String contained) {
        assertTrue(String.format("%s should contain %s",containing,contained),containing.contains(contained));
    }
}
