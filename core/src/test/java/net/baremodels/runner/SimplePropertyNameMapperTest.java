package net.baremodels.runner;

import net.baremodels.model.Property;
import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import static org.junit.Assert.assertEquals;
import static test.mock.Mocks._;

public class SimplePropertyNameMapperTest {

    Property property;
    SimplePropertyNameMapper testObject = new SimplePropertyNameMapper();

    @Before
    public void init() {
        Mocks.init(this);
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
        _(source); property.name();
        assertEquals(target,testObject.getName(property));
    }

}
