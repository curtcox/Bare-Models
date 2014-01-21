package net.baremodels.runner.modelrenderer;

import net.baremodels.model.ListModel;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.runner.modelrenderer.SimplePropertyNameMapper;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.assertEquals;
import static test.mock.Mocks._;

public class SimplePropertyNameMapperTest {

    Property property;
    Property listProperty;
    Model model;
    ListModel listModel;
    SimplePropertyNameMapper testObject = new SimplePropertyNameMapper();

    @Before
    public void init() {
        Mocks.init(this);

        _(model);         property.model();
        _(listModel);     listProperty.model();
    }

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
        map_list_to_name(source,target);
        map_value_to_name_plus_toString(source, target + ": " + model);
    }

    private void map_list_to_name(String source, String target) {
        _(source); listProperty.name();
        assertEquals(target,testObject.getName(listProperty));
    }

    private void map_value_to_name_plus_toString(String source, String target) {
        _(source); property.name();
        assertEquals(target,testObject.getName(property));
    }

}
