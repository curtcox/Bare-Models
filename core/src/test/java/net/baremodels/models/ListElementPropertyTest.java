package net.baremodels.models;

import net.baremodels.model.Property;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListElementPropertyTest {

    ModelFactory modelFactory = new ObjectModelFactory();

    @Test
    public void is_property() {
        ListElementProperty testObject = new ListElementProperty(Collections.singletonList(""),0,modelFactory);
        assertTrue(testObject instanceof Property);
    }

    @Test
    public void toString_returns_value_from_list_element_toString() {
        Object value = new Object();
        ListElementProperty testObject = new ListElementProperty(Collections.singletonList(value),0,modelFactory);
        assertEquals(value.toString(),testObject.toString());
    }

    @Test
    public void property_contains_list_value_at_index() {
        List list = new ArrayList();
        for (int i=0; i<10; i++) {
            list.add(i + "glyph");
        }
        for (int i=0; i<10; i++) {
            ListElementProperty testObject = new ListElementProperty(list,i,modelFactory);
            String value = i + "glyph";
            assertEquals(value,testObject.get());
            assertEquals(i + "", testObject.name());
            assertTrue(testObject.model() instanceof StringConstantModel);
            assertEquals(value,((StringConstantModel) testObject.model()).value);
        }
    }

}
