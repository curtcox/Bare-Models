package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.model.Property;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SimplePropertyNameMapperTest {

    String name;
    Property property = new Property() {
        @Override public Object get() { return null; }
        @Override public void set(Object o) {}
        @Override public Model model() { return null; }
        @Override public Map<String, Property> meta() { return null; }
        @Override public String name() { return name; }
    };
    SimplePropertyNameMapper testObject = new SimplePropertyNameMapper();

    @Test
    public void capitalize_single_words() {
        map("first","First");
        map("last","Last");
    }

    @Test
    public void capitalize_camel_case_words_and_add_spaces() {
        map("firstName","First Name");
        map("lastName","Last Name");
        map("serviceModelNumber","Service Model Number");
    }

    @Test
    public void capitalize_snake_case_words_and_add_spaces() {
        map("first_name","First Name");
        map("last_name","Last Name");
        map("service_model_number","Service Model Number");
    }

    private void map(String source, String target) {
        name = source;
        assertEquals(target,testObject.getName(property));
    }

}
