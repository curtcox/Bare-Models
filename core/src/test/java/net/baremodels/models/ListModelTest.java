package net.baremodels.models;

import net.baremodels.model.Model;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListModelTest {

    List list = new ArrayList();
    ListModel testObject = new ListModel(list);

    @Test
    public void is_a_model() {
         assertTrue(testObject instanceof Model);
    }

    @Test
    public void properties_is_empty_when_list_is_empty() {
        assertTrue(testObject.properties().isEmpty());
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

}
