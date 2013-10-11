package net.baremodels.models;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.Team;
import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectListModelTest {

    List list = new ArrayList();
    ModelFactory modelFactory = new ObjectModelFactory();
    ObjectListModel testObject = new ObjectListModel(list,"list",modelFactory);

    @Test
    public void is_a_model() {
         assertTrue(testObject instanceof Model);
    }

    @Test
    public void getList_returns_list_from_constructor() {
        assertSame(list,testObject.list);
        assertSame(list,testObject.getList());
    }

    @Test
    public void properties_is_empty_when_list_is_empty() {
        assertTrue(testObject.properties().isEmpty());
    }

    @Test
    public void has_a_name_meta() {
        assertNotNull(testObject.meta());
        assertTrue(testObject.meta().containsKey(Property.NAME));
    }

    @Test
    public void list_name_is_list_when_no_name_provided() {
        assertEquals("list",testObject.name());
    }

    @Test
    public void list_name_is_given_name_when_provided() {
        assertEquals("given",new ObjectListModel(list,"given",modelFactory).name());
    }

    @Test
    public void get_throws_IndexOutOfBoundsException_for_bad_index() {
        try {
            testObject.properties().get(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // OK
        }
    }

    @Test
    public void properties_returns_map_containing_list_values_by_index() {
        for (int i = 0; i<10; i++) {
            list.add(i + " value");
        }
        for (int i = 0; i<10; i++) {
            assertEquals(i + " value",testObject.properties().get(i).get());
        }
    }

    @Test
    public void properties_returns_map_containing_properties() {
        for (int i = 0; i<10; i++) {
            list.add(i + " value");
        }
        int i = 0;
        for (Property property : testObject.properties().values()) {
            assertEquals(i + " value",property.get());
            i++;
            if (i>10) {
                fail();
            }
        }
        assertEquals(i,10);
    }

    @Test
    public void toString_contains_list_toString() {
        list.add("Tinker");
        list.add("Tailor");
        list.add("Soldier");
        list.add("Chance");
        assertTrue(testObject.toString().contains(list.toString()));
    }

    @Test
    public void equal_lists() {
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        ObjectListModel testObject1 = new ObjectListModel(list1,"list",modelFactory);
        ObjectListModel testObject2 = new ObjectListModel(list2,"list",modelFactory);
        for (Object o : new Object[] {8,6,7,5,3,0,9, "Jenni", "Jenni"}) {
            list1.add(o);
            list2.add(o);
            assertTrue(testObject1.equals(testObject2));
            assertTrue(testObject2.equals(testObject1));
        }
    }

    @Test
    public void unequal_lists() {
        ObjectListModel testObject1 = new ObjectListModel(Collections.singletonList("a"),"list",modelFactory);
        ObjectListModel testObject2 = new ObjectListModel(Collections.singletonList("b"),"list",modelFactory);
        assertFalse(testObject1.equals(testObject2));
        assertFalse(testObject2.equals(testObject1));
    }

    @Test
    public void hashCode_delegates_to_list() {
        for (Object o : new Object[] {8,6,7,5,3,0,9, "Jenni", "Jenni"}) {
            list.add(o);
            assertEquals(list.hashCode(), testObject.hashCode());
        }
    }

    @Test
    public void list_items_changes_with_object_list() {
        Nucleus nucleus = new Nucleus();
        Model model = modelFactory.of(nucleus);
        ListModel teams = (ListModel) model.properties().get("teams").model();
        assertFalse(teams.properties().values().iterator().hasNext());

        nucleus.teams.add(new Team());
        assertTrue(teams.properties().values().iterator().hasNext());
    }

}
