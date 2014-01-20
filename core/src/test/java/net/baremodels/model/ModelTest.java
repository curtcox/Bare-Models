package net.baremodels.model;

import org.junit.Before;
import org.junit.Test;
import test.mock.Mocks;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertSame;

public class ModelTest {

    String operationName = "operation " + toString();
    Operation operation;
    Map<String, Operation> operations = new HashMap<>();
    Model testObject = new Model() {
        @Override public Map<?, Property> properties()  {  return null;  }
        @Override public Map<?, Operation> operations() { return operations; }
        @Override public Map<String, Property> meta()   { return null; }
    };

    @Before
    public void init() {
        Mocks.init(this);
        operations.put(operationName,operation);
    }

    @Test
    public void operation_returns_named_operation() {
        Operation actual = testObject.operation(operationName);

        assertSame(operation,actual);
    }
}
