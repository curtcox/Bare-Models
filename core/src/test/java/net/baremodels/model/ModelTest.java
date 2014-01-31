package net.baremodels.model;

import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import static test.mock.Mocks._;
import static test.mock.Mocks.verify;

public class ModelTest {

    String operationName = "operation " + toString();
    Operation operation;
    Map<String, Operation> operations = new HashMap<>();
    String propertyName = "property name " + toString();
    String propertyValue = "property value " + toString();

    Property property;
    Map<String, Property> properties = new HashMap<>();
    Model testObject = new Model() {
        @Override public Map<?, Property> properties()  {  return properties;  }
        @Override public Map<?, Operation> operations() { return operations; }
        @Override public Map<String, Property> meta()   { return null; }
    };

    @Before
    public void init() {
        Mocks.init(this);
        operations.put(operationName,operation);
        properties.put(propertyName,property);
        _(propertyValue); property.get();
    }

    @Test
    public void operation_returns_named_operation_if_it_exists() {
        Operation actual = testObject.operation(operationName);

        assertSame(operation,actual);
    }

    @Test
    public void operation_throws_IllegalArgumentException_for_name_with_no_matching_operation() {
        String operationName = "beAwesome";
        try {
            testObject.operation(operationName);
            fail();
        } catch (IllegalArgumentException e) {
            String message = String.format("The operation %s is not defined on %s",operationName,testObject);
            assertEquals(message,e.getMessage());
        }
    }

    @Test
    public void get_returns_named_property_value() {
        Object actual = testObject.get(propertyName);

        assertSame(propertyValue,actual);
    }

    @Test
    public void set_sets_named_property_value() {
        _(); property.set(propertyValue);

        testObject.set(propertyName,propertyValue);

        verify(); property.set(propertyValue);
    }

}
