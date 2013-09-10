package net.baremodels.models;

import net.baremodels.model.Model;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectListModelTest {

    List list = new ArrayList();
    ObjectListModel testObject = new ObjectListModel(list);

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
    public void get_throws_IndexOutOfBoundsException_for_bad_index() {
        try {
            testObject.properties().get(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // OK
        }
    }

    @Test
    public void properties_returns_map_containing_list_values() {
        for (int i = 0; i<10; i++) {
            list.add(i + " value");
        }
        for (int i = 0; i<10; i++) {
            assertEquals(i + " value",testObject.properties().get(i).get());
        }
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
        ObjectListModel testObject1 = new ObjectListModel(list1);
        ObjectListModel testObject2 = new ObjectListModel(list2);
        for (Object o : new Object[] {8,6,7,5,3,0,9, "Jenni", "Jenni"}) {
            list1.add(o);
            list2.add(o);
            assertTrue(testObject1.equals(testObject2));
            assertTrue(testObject2.equals(testObject1));
        }
    }

    @Test
    public void unequal_lists() {
        ObjectListModel testObject1 = new ObjectListModel(Collections.singletonList("a"));
        ObjectListModel testObject2 = new ObjectListModel(Collections.singletonList("b"));
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

}
